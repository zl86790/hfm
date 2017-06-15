

import com.qingxin.service.UserService;
import com.qingxin.user.bean.User;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	public void testCreate(){
		User anakinSkywalker = new User();
		anakinSkywalker.setMailAddress("andy@example.com");
		User obiwanKonobi = new User();
		obiwanKonobi.setMailAddress("john@example.com");
		
		UserService service = UserService.getInstance();
		service.create(anakinSkywalker, obiwanKonobi);
	}

}
