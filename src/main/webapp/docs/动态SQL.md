<font color="#4590a3" size = "6px"><center>动态SQL</center ></font>
```XML
• if:判断
• choose (when, otherwise):分支选择；带了break的swtich-case
	如果带了id就用id查，如果带了lastName就用lastName查;只会进入其中一个
• trim 字符串截取(where(封装查询条件), set(封装修改条件))
• foreach 遍历集合
```
<font color="#4590a3" size = "4px"><center>批量保存</center ></font>
 ######Mysql下
```XML
必要时才打开
jdbc.url=jdbc:mysql://localhost:3306/mybatis?allowMultiQueries=true
```
 ######Oracle下
 ```XML
 	 	1、多个insert放在begin - end里面
 	 		begin
 			    insert into employees(employee_id,last_name,email)
 			    values(employees_seq.nextval,'test_001','test_001@atguigu.com');
 			    insert into employees(employee_id,last_name,email)
 			    values(employees_seq.nextval,'test_002','test_002@atguigu.com');
 			end;
 		2、利用中间表：
 			insert into employees(employee_id,last_name,email)
 		       select employees_seq.nextval,lastName,email from(
 		              select 'test_a_01' lastName,'test_a_e01' email from dual
 		              union
 		              select 'test_a_02' lastName,'test_a_e02' email from dual
 		              union
 		              select 'test_a_03' lastName,'test_a_e03' email from dual
 		       )
 	 -->
 	 <insert id="addEmps" databaseId="oracle">
 	 	<!-- oracle第一种批量方式 -->
 	 	<!-- <foreach collection="emps" item="emp" open="begin" close="end;">
 	 		insert into employees(employee_id,last_name,email)
 			    values(employees_seq.nextval,#{emp.lastName},#{emp.email});
 	 	</foreach> -->

 	 	<!-- oracle第二种批量方式  -->
 	 	insert into employees(
 	 		<!-- 引用外部定义的sql -->
 	 		<include refid="insertColumn">
 	 			<property name="testColomn" value="abc"/>
 	 		</include>
 	 	)
 	 			<foreach collection="emps" item="emp" separator="union"
 	 				open="select employees_seq.nextval,lastName,email from("
 	 				close=")">
 	 				select #{emp.lastName} lastName,#{emp.email} email from dual
 	 			</foreach>
 	 </insert>

 	 <!-- 两个内置参数：
 	 	不只是方法传递过来的参数可以被用来判断，取值。。。
 	 	mybatis默认还有两个内置参数：
 	 	_parameter:代表整个参数
 	 		单个参数：_parameter就是这个参数
 	 		多个参数：参数会被封装为一个map；_parameter就是代表这个map

 	 	_databaseId:如果配置了databaseIdProvider标签。
 	 		_databaseId就是代表当前数据库的别名oracle
 	  -->

 	  <!--public List<Employee> getEmpsTestInnerParameter(Employee employee);  -->
 	  <select id="getEmpsTestInnerParameter" resultType="com.atguigu.mybatis.bean.Employee">
 	  		<!-- bind：可以将OGNL表达式的值绑定到一个变量中，方便后来引用这个变量的值 -->
 	  		<bind name="_lastName" value="'%'+lastName+'%'"/>
 	  		<if test="_databaseId=='mysql'">
 	  			select * from tbl_employee
 	  			<if test="_parameter!=null">
 	  				where last_name like #{lastName}
 	  			</if>
 	  		</if>
 	  		<if test="_databaseId=='oracle'">
 	  			select * from employees
 	  			<if test="_parameter!=null">
 	  				where last_name like #{_parameter.lastName}
 	  			</if>
 	  		</if>
 	  </select>

 	  <!--
 	  	抽取可重用的sql片段。方便后面引用
 	  	1、sql抽取：经常将要查询的列名，或者插入用的列名抽取出来方便引用
 	  	2、include来引用已经抽取的sql：
 	  	3、include还可以自定义一些property，sql标签内部就能使用自定义的属性
 	  			include-property：取值的正确方式${prop},
 	  			#{不能使用这种方式}
 	  -->
 	  <sql id="insertColumn">
 	  		<if test="_databaseId=='oracle'">
 	  			employee_id,last_name,email
 	  		</if>
 	  		<if test="_databaseId=='mysql'">
 	  			last_name,email,gender,d_id
 	  		</if>
 	  </sql>
```