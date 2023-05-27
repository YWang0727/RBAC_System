package com.yuewang.rbac.security.plugin;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.yuewang.rbac.model.VO.UserDetailsVO;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyPaginationInterceptor
 * @Description implements from Mybatis Plus's InnerInterceptor interface, do interception. Enables control of data permissions.
 * @Author Yue Wang
 * @Date 2023/5/15 17:01
 **/

@Slf4j
public class MyPaginationInterceptor implements InnerInterceptor {

    @Override
    public void beforePrepare(StatementHandler statementHandler, Connection connection, Integer transactionTimeout) {
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // get current SQL query, and judge if need interception
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        // if is paging, e.g.: com.imyuewang.rbac.mapper.selectPage, then do the interception
        // 'id' is the full path name of mapper method
        String id = mappedStatement.getId();
        log.info("mapper: ==> {}", id);
        // can be stored in a collection if there are many methods, and judge if current interception is in the collection
        // if is not the specified method, end interception
        if (!id.startsWith("com.imyuewang.rbac.mapper.DataMapper.selectPage")) {
            return;
        }

        // get the original sql query
        String sql = statementHandler.getBoundSql().getSql();
        log.info("original sql query： ==> {}", sql);
        sql = getSql(sql);
        // modify sql query
        metaObject.setValue("delegate.boundSql.sql", sql);
        log.info("sql query after modifying：==>{}", sql);
    }

    //parse the sql query, and return the new sql query
    private String getSql(String sql) {
        try {
            // parser
            Statement stmt = CCJSqlParserUtil.parse(sql);
            Select selectStatement = (Select) stmt;
            PlainSelect ps = (PlainSelect) selectStatement.getSelectBody();
            // get table info
            FromItem fromItem = ps.getFromItem();
            Table table = (Table) fromItem;
            String mainTable = table.getAlias() == null ? table.getName() : table.getAlias().getName();

            List<Join> joins = ps.getJoins();
            if (joins == null) {
                joins = new ArrayList<>(1);
            }

            // create join conditions
            Join join = new Join();
            join.setInner(true);
            join.setRightItem(new Table("user_company uc"));
            // 1st: join on company_id
            EqualsTo joinExpression = new EqualsTo();
            joinExpression.setLeftExpression(new Column(mainTable + ".company_id"));
            joinExpression.setRightExpression(new Column("uc.company_id"));
            // 2nd: join on current user id
            EqualsTo userIdExpression = new EqualsTo();
            userIdExpression.setLeftExpression(new Column("uc.user_id"));
            // get current user
            UserDetailsVO user = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userIdExpression.setRightExpression(new LongValue(user.getUser().getId()));
            // join 2 conditions
            join.setOnExpression(new AndExpression(joinExpression, userIdExpression));
            joins.add(join);
            ps.setJoins(joins);

            // modify the original sql query
            sql = ps.toString();
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
