## bug fix

    *2016-05-13
       1.启动tomcat时报 类加载器泄露；
       ```
            registered the JDBC driver [com.mysql.jdbc.Driver] but failed to unregister it when the web application was stopped.
            To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
       ```

         使用了JPA的Pageable分页导致。但目前不确定pageable导致的原因所在。

          有可能是dbcp连接池低版本没有关闭jdbc连接所致。

          使用hibernate 的时候也出现了。




