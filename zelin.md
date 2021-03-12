###zelin-plugin (zelin-plugin-mybatis-starter)
> entity/mapper/mapper.xml/service/controller代码生成
>> resources/example.properties
``` properties
# ----------------------- Global -----------------------
# 生成所属模块
mbpg.global.generatorModule=jnsw-module/jnsw-eds/jnsw-base
# 生成基础路径
mbpg.global.generatorBasePath=src/main/kotlin
# 作者
mbpg.global.author=JN
# 是否是Kotlin模式
mbpg.global.isKotlin=true
# 是否文件覆盖
mbpg.global.isFileOverride=true

# Id生成策略
######################
# AUTO 数据库ID自增
# NONE 该类型为未设置主键类型
# INPUT 用户输入ID
# ASSIGN_ID 分配ID (主键类型为number或string）默认实现雪花算法
# ASSIGN_UUID 分配UUID (主键类型为 string)
######################
mbpg.global.idType=ASSIGN_ID
# SQLXML是否生成 ResultMap
mbpg.global.isBaseResultMap=true
# SQLXML是否生成 Column List
mbpg.global.isBaseColumnList=true
# 是否添加Swagger注解
mbpg.global.isSwagger2=false
# ----------------------- Global -----------------------

# ----------------------- DataSource -----------------------
# 数据库类型
######################
# MYSQL=MySql数据库
# MARIADB=MariaDB数据库
# ORACLE=racle
# DB2=DB2数据库
# H2=H2数据库
# HSQL=HSQL数据库
# SQLITE(=SQLite数据库
# POSTGRE_SQL=Postgre数据库
# SQL_SERVER2005=SQLServer2005数据库
# SQL_SERVER=SQLServer数据库
# DM=达梦数据库
# OTHER=其他数据库"
######################
mbpg.datasource.type=MYSQL
# 数据库连接
mbpg.datasource.url=jdbc:mysql://localhost:3306/jns_db?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
# 驱动名称
mbpg.datasource.driverName=com.mysql.cj.jdbc.Driver
# 账号
mbpg.datasource.username=root
# 密码
mbpg.datasource.password=1234
# ----------------------- DataSource -----------------------

# ----------------------- Strategy -----------------------
# 生成表名集合
mbpg.strategy.tableNames=tm_app,tm_user
# 是否生成Lombok Entity
mbpg.strategy.isLombokEntity=false
# 生成乐观锁字段
mbpg.strategy.versionFieldName=version
# 填充字段操作，添加，修改操作
######################
# DEFAULT,
# INSERT,
# UPDATE,
# INSERT_UPDATE;
######################
# 创建时间字段名称
mbpg.strategy.tableFill.create_time=INSERT
# 修改时间字段名称
mbpg.strategy.tableFill.update_time=UPDATE
# ----------------------- Strategy -----------------------

# ----------------------- Package -----------------------
# 基础包名
mbpg.pkg.basePackageName=com.jnsw.module.base
# Entity映射实体包名
mbpg.pkg.entityPackageName=entity
# Mapper包名（DAO）
mbpg.pkg.mapperPackageName=mapper
# SQLXML生成路径
# 将SQLXML生成到resources资源目录中，如"src/main/resources/....." 配置时无需带有src/main/resources地址，只需配置后续即可
mbpg.pkg.sqlXMLPackageName=mapper
# Service包名
mbpg.pkg.servicePackageName=service
# Service实现
mbpg.pkg.serviceImplPackageName=service.impl
# Controller包名
# mbpg.pkg.controllerPackageName=controller
# ----------------------- Package -----------------------

# ----------------------- Template -----------------------
# 是否生成Persistent
mbpg.template.isGenPersistent=true
# 是否生成Service
mbpg.template.isGenService=true
# 是否生成Service实现
mbpg.template.isGenServiceImpl=true
# 是否生成Controller
# mbpg.template.isGenController=false
# ----------------------- Template -----------------------
```
>> Generator.java 生成代码
```java
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;

public class Generator {

    private static final String properties = "example.properties";

    public static void main(String[] args) {
        final String classPath = ClassUtil.getClassPath();
        final String propertiesPath = classPath + properties;
        MbpGenerator.Companion.execute(FileUtil.newFile(propertiesPath));
    }

}
```
> 表填充
>> application/bootstrap.yml
```yaml
zelin:
  plugin:
    tablefill:
      # 是否开启表填充
      enabled-table-fill: true
      field-fill-strategy:
        # 填充字段策略
        strategy: INSERT_UPDATE
        # 是否严格填充
        # 严格填充，无论获得属性是否有值都将被填充
        # 非严格填充，当获取属性有值则忽略填充
        strict-fill: false
        # 填充字段名称
        field-name: example
        # 字段值填充策略
        # com.zelin.plugin.mybatis.config.tablefill.TableFillConfig.FieldValueStrategy
        field-value: om.zelin.plugin.mybatis.config.tablefill.TableFillConfig.DateNowStrategy
```
> 分页
>> application/bootstrap.yml
```yaml
zelin:
  plugin:
    pagination:
      # 是否开启分页
      enabled-pagination: false
      # 溢出总页数后是否进行处理
      overflow: false
      # 单页分页条数限制
      max-limit: 0
      # 数据库类型
      db-type: MYSQL
      # 方言实现类
      dialect: com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect
```
### zelin-plugin (zelin-plugin-web-starter)
> Web全局异常配置（非Reactive Web）
>> application/bootstrap.yml
```yaml
zelin:
  plugin:
    web: 
      error:
        # 是否开启全局异常控制
        enabled-web-global-exception: false
        # 是否是生产模式
        is-prod: false
```
> Web Fastjson配置
>> application/bootstrap.yml
```yaml
zelin:
  plugin:
    web: 
      fastjson:
        # 是否开启FastJson Message Converters
        enabled-message-converters: false
        # 序列化集合
        # 为空默认为如下
        # 格式化
        # SerializerFeature.PrettyFormat,
        # 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        # SerializerFeature.DisableCircularReferenceDetect,
        # 字符类型字段如果为null,输出为"",而非null
        # SerializerFeature.WriteNullStringAsEmpty,
        # List字段如果为null,输出为[],而非null
        # SerializerFeature.WriteNullListAsEmpty
        serializer-feature: false
        # 日期格式化
        # 默认=yyyy-MM-dd HH:mm:ss
        date-format: 
```
> Undertow Https配置
```yaml
zelin:
  plugin:
    web:
      # 是否启用Undertow Https
      enabled-undertow-https: false
      # Https 访问端口
      https-port: 8081
      # Http 访问端口
      http-port: 8080
```
> validator支持
>> application/bootstrap.yml
```yaml
zelin:
  plugin:
    web:
      enabled-validator: false
```
> Swagger2 Gateway支持。添加@EnabledSwagger2Gateway注解即可
###zelin-plugin (zelin-plugin-redis-starter)
```java
public class Example {

    /**
     * Redis批量操作模板
     */
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;
    
}
```
###zelin-plugin (zelin-plugin-jwt-starter)
```yaml
zelin:
  plugin:
    web:
      jwt:
        # 是否开启Jwt工具处理及redis存储
        enabled: false
        # Jwt传输内容是否加密
        encryp: false
        # 加盐密钥
        secret: P@SSw0rd
        # Token有效时间 单位：秒 默认=86400S=24小时
        expiration-seconds: 86400s
        # 是否禁止重复登录
        # false 禁止重复登录关闭， true 禁止重复登录开启
        repeat: false
```
```java
public class Example {

    /**
     * Jwt Token工具类
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Jwt Token Redis缓存操作
     */
    @Autowired
    private JwtTokenRedisRepository jwtTokenRedisRepository;

    /**
     * Jwt Token 相关业务实现
     */
    @Autowired
    private JwtAccessService jwtAccessService;

}
```