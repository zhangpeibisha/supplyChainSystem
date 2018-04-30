    <resultMap id="BaseResultMap" type="${oneself.type}">
    <#list oneself.params as param>
    <#if param.name == "id" >
        <id column="id" property="id" jdbcType="INTEGER"/>
    <#else >
        <result column="${param.name}" property="${param.name}" jdbcType="${param.type}"/>
    </#if>
    </#list>
    <#if others?has_content>
        <#list others as other>
        <#if other.columnType == 1>
        <association property="${other.name}" javaType="${other.type}">
            <#list other.params as other_param>
            <#if other_param.name == "id" >
            <id column="id" property="id" jdbcType="INTEGER"/>
            <#else >
            <result column="${other_param.name}" property="${other_param.name}" jdbcType="${other_param.type}"/>
            </#if>
            </#list>
        </association>
        </#if>
        <#if other.columnType == 2>
        <collection property="${other.name}" ofType="${other.type}" column="id">
            <#list other.params as other_param>
            <#if other_param.name == "id" >
            <id column="id" property="id" jdbcType="INTEGER"/>
            <#else >
            <result column="${other_param.name}" property="${other_param.name}" jdbcType="${other_param.type}"/>
            </#if>
            </#list>
        </collection>
        </#if>
        </#list>
    </#if>
    </resultMap>
    <insert id="insert" parameterType="${oneself.type}">
        insert into `${model_name}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list oneself.params as param>
            <if test="${param.name} != null">
                `${param.name}`,
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list oneself.params as param>
            <if test="${param.name} != null">
            ${r"#{"}${param.name},jdbcType=${param.type}${r"}"},
            </if>
        </#list>
        </trim>
    </insert>
    <delete id="delete" parameterType="java.lang.Integer">
        delete  from `${model_name}` where id = ${r"#{"}id,jdbcType=INTEGER${r"}"}
    </delete>
    <update id="update" parameterType="${oneself.type}">
        updata `${model_name}`
        set
        <trim prefix="" suffix="" suffixOverrides=",">
        <#list oneself.params as param>
            <if test="true">
            `${param.name}` = ${r"#{"}${param.name},jdbcType=${param.type}${r"}"},
            </if>
        </#list>
        </trim>
        where id = ${r"#{"}id,jdbcType=INTEGER${r"}"}
    </update>
    <select id="select" parameterType="int" resultMap="BaseResultMap">
        select * from `${model_name}` where id = ${r"#{"}id,jdbcType=INTEGER${r"}"}
    </select>
    <select id="list" resultMap="BaseResultMap">
        select * from `${model_name}`
        <if test="conditions != null">
            where @{conditions}
        </if>
        <if test="order != null and sort != null">
            order by @{order} @{sort}
        </if>
        <if test="limit != -1 and limit != -1">
            limit ${r"#{offset,jdbcType=INTEGER}"},${r"#{limit,jdbcType=INTEGER}"}
        </if>
    </select>