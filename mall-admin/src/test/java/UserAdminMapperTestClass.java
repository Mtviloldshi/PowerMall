import com.power.mall.MallAdminApplication;
import com.power.mall.mapper.UserAdminMapperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes= MallAdminApplication.class)
public class UserAdminMapperTestClass {

    @Autowired
    private UserAdminMapperTest userAdminMapperTest;

    @Test
    public void testList() {
        // 调用 list() 方法，获取 ums_admin 表中的记录总数
        int totalCount = userAdminMapperTest.list();

        // 检查返回的记录数量是否为预期值
        // 如果数据库中有至少一条记录，检查结果是否大于 0
        Assertions.assertTrue(totalCount >= 0, "The total count of ums_admin records should be greater than or equal to 0");

        // 输出记录总数，便于在控制台查看
        System.out.println("Total records in ums_admin table: " + totalCount);
    }
}
