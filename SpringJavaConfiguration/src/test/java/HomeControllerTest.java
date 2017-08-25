import com.sxm.spring.controller.HomeController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/3/5 0:18
 * @since 0.1
 */
public class HomeControllerTest {

    @Test
    public void testIndexView() throws Exception {

        HomeController controller = new HomeController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/index")).//
                andExpect(MockMvcResultMatchers.view().name("front/home/home")).//
                andDo(MockMvcResultHandlers.print()).andReturn();

        Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("serverTime"));

    }

}
