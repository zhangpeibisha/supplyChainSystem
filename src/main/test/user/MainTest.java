package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nix.common.enums.RoleEnum;
import org.nix.dao.mapper.CityMapper;
import org.nix.dao.mapper.RoleMapper;
import org.nix.dao.mapper.UserMapper;
import org.nix.model.RoleModel;
import org.nix.model.UserModel;
import org.nix.model.city.City;
import org.nix.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MainTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 加入地址、角色写入用户信息测试
     */
    @Test
    public void insertUserTest() {
        City chongqing = cityMapper.findCityById(1);
        UserModel userModel = new UserModel();
        RoleModel roleModel = roleMapper.selectRoleByName(RoleEnum.ROLE_USER.getRoleName());
        userModel.setUserName("毕沙");
        userModel.setPhone("15334503852");
        userModel.setPassWord("e10adc3949ba59abbe56e057f20f883e"); // 明文：123456
        userModel.setNickName("中国金业集团");
        userModel.setAddress(chongqing);
        userModel.setRoleModel(roleModel);
        userMapper.insert(userModel);
    }

    /**
     * 此方法用于容器初始化时，
     * 将RoleEnum枚举类中的枚举值取出放入到数据库中
     */
    @Test
    public void insertRoleTest() {
        List<RoleModel> roleModels = new ArrayList<>();
        Map<String, String> map = getRoleEnumValueToMap();
        for (Map.Entry<String, String> v : map.entrySet()) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRoleName(v.getKey());
            roleModel.setMsg(v.getValue());
            roleModels.add(roleModel);
        }
        roleMapper.insertRoleList(roleModels);
    }

    /**
     * todo: 获取角色枚举的所有属性值
     *
     * @return
     */
    public static Map<String, String> getRoleEnumValueToMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        for (int i = 0; i < RoleEnum.values().length; i++) {
            map.put(RoleEnum.values()[i].getRoleName(), RoleEnum.values()[i].getDes());
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String, String> map = getRoleEnumValueToMap();
        System.out.println();
    }

    @Autowired
    private UserService userService;

    @Test
    public void selectUserTest(){
        String nickName = "4";
        String passWord = "4";

        UserModel userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setPassWord(passWord);
        UserModel model = userService.login(userModel);
        System.out.println();
    }
}
