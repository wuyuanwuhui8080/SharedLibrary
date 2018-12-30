package com.share.sharedlibrary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.pojo.SharedEmail;
import com.share.service.SharedEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharedlibraryApplicationTests {

    @Resource
    private SharedEmailService sharedEmailService;

    @Test
    public void contextLoads() {
        Page<SharedEmail> page = new Page<>(1, 8);
        page = (Page<SharedEmail>) sharedEmailService.page(page/*, new QueryWrapper<SharedEmail>()
                .eq("me_id", "857ebe90f0fa1813b33787c393c2d558")
                .orderByDesc("creation_date")*/
        );
        page.getRecords().forEach(employee -> System.out.println(employee.toString()));
        System.out.println("============获取分页相关的信息========================");
        System.out.println("总条数" + page.getTotal());
        System.out.println("当前页码" + page.getCurrent());
        System.out.println("总页码" + page.getPages());
        System.out.println("每页显示的条数" + page.getSize());
        System.out.println("是否有上一页" + page.hasPrevious());
        System.out.println("是否有下一页" + page.hasNext());

        //将查询结果封装到page对象中

        /**
         * 之后做页面数据传递就可以将page对象返回到JSP,经过方法就可以取到的值
         * */


    }

}
