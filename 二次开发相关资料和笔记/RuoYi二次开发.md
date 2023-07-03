### 1. 设计表结构

- 若依框架数据库表设计结构中都存在以下的5个字段，在设计表时应该遵循这个规范：

![image-20230630164846582](RuoYi二次开发.assets/image-20230630164846582.png)

### 2. 创建子模块以及各层

- 创建子模块时，注意在根目录和admin目录中的pom.xml文件中添加子模块依赖，不然可能会出现**controller层的接口识别不到**或者其他问题，具体参考官方文档的后台手册目录下的新建子模块：

<img src="RuoYi二次开发.assets/image-20230703144300304.png" alt="image-20230703144300304" style="zoom:67%;" />

- 注意各层的文件目录位置

<img src="RuoYi二次开发.assets/image-20230703103400694.png" alt="image-20230703103400694" style="zoom:80%;" />

### 3. 常规手写开发

**常规开发完成一个厂商列表查询接口**

1. 完成实体类

- 注意在pom文件中导入common通用模块，才能使用里面的类和三方包

```xml
    <dependencies>

        <!-- 通用工具-->
        <dependency>
            <groupId>com.ruoyi</groupId>
            <artifactId>ruoyi-common</artifactId>
        </dependency>

    </dependencies>
```

- 实体类

```java
@EqualsAndHashCode(callSuper = true)
@Data
public class PillFactory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 厂商Id */
    private Long FactoryId;

    /** 厂商名称 */
    private String FactoryName;

    /** 厂商编号 */
    private String FactoryCode;

    /** 联系人 */
    private String contact;

    /** 电话 */
    private String phone;

    /** 关键字 */
    private String keyword;

    /** 状态 */
    private String status;
}
```

2. 编写mapper层和xml文件

```xml
    <resultMap type="PillFactory" id="PillFactoryResult">
        <id     property="factoryId"        column="factory_id"       />
        <result property="factoryCode"      column="factory_code"     />
        <result property="factoryName"      column="factory_name"     />
        <result property="contact"      column="contact"     />
        <result property="phone"      column="phone"     />
        <result property="keyword"      column="keyword"     />
        <result property="status"        column="status"        />
        <result property="createBy"      column="create_by"     />
        <result property="createTime"    column="create_time"   />
        <result property="updateBy"      column="update_by"     />
        <result property="updateTime"    column="update_time"   />
        <result property="remark"        column="remark"        />
    </resultMap>

    <sql id="selectPillFactoryVo">
        select factory_id, factory_name, factory_code, contact, phone, keyword, status, create_by, create_time,
               update_by, update_time, remark
        from pill_factory
    </sql>

    <select id="selectPillFactoryList" parameterType="PillFactory" resultMap="PillFactoryResult">
        <include refid="selectPillFactoryVo"/>
        <where>
            <if test="factoryCode != null and factoryCode != ''">
                AND factory_code like concat('%', #{factoryCode}, '%')
            </if>
            <if test="status != null and status != ''">
                AND status = #{status}
            </if>
            <if test="factoryName != null and factoryName != ''">
                AND factory_name like concat('%', #{factoryName}, '%')
            </if>
            <if test="contact != null and contact != ''">
                AND contact like concat('%', #{contact}, '%')
            </if>
            <if test="phone != null and phone != ''">
                AND phone like concat('%', #{phone}, '%')
            </if>
            <if test="keyword != null and keyword != ''">
                AND keyword like concat('%', #{keyword}, '%')
            </if>
        </where>
    </select>
```

3. 编写service层

```java
@Service
public class PillFactoryServiceImpl implements IPillFactoryService {

    @Autowired
    private PillFactoryMapper pillFactoryMapper;

    @Override
    public List<PillFactory> selectPostList(PillFactory factory) {
        return pillFactoryMapper.selectPillFactoryList(factory);
    }
}
```

4. 编写controller层

```java
@RestController
@RequestMapping("/pill/factory")
public class PillFactoryController extends BaseController {

    @Autowired
    private IPillFactoryService factoryService;

    /**
     * 获取厂商列表
     */
//    @PreAuthorize("@ss.hasPermi('pill:factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(PillFactory pillFactory)
    {
        startPage();
        List<PillFactory> list = factoryService.selectPillFactoryList(pillFactory);
        return getDataTable(list);
    }
}
```

### 4. 代码自动生成

1. 修改generator模块中的配置文件

```yml
# 代码生成
gen: 
  # 作者
  author: jiangxiong
  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool
  packageName: com.ruoyi.pill
  # 自动去除表前缀，默认是false
  autoRemovePre: false
  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）
  tablePrefix: pill_
```

2. 在若依管理后台，使用代码生成工具

![image-20230703152810210](RuoYi二次开发.assets/image-20230703152810210.png)

#### 4.1 代码自动生成-后端开发

- 将自动生成的src目录复制到对应子模块下面即可

#### 4.2 代码自动生成-前端开发

1. 将自动生成的代码复制到对应api和views目录中。
2. 自定义图标：将iconfont中下载的图标svg文件复制到项目的对应目录中：

![image-20230703161449703](RuoYi二次开发.assets/image-20230703161449703.png)

3. 新增自定义的菜单：

- 添加主目录

<img src="RuoYi二次开发.assets/image-20230703161734106.png" alt="image-20230703161734106" style="zoom:80%;" />

- 添加子菜单：

<img src="RuoYi二次开发.assets/image-20230703162141210.png" alt="image-20230703162141210" style="zoom: 67%;" />

- 刷新页面，即可看见新增的菜单

4. 根据需求原型，修改Index.vue组件

