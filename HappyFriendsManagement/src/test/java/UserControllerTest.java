

import com.qingxin.service.UserService;
import com.qingxin.user.bean.User;
import com.qingxin.user.exception.CreateConflictException;
import com.qingxin.user.exception.UserNotFoundException;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	public void testCreate(){
		User anakinSkywalker = new User();
		anakinSkywalker.setMailAddress("andy@example.com");
		User obiwanKonobi = new User();
		obiwanKonobi.setMailAddress("john@example.com");
		
		UserService service = UserService.getInstance();
		try {
			service.create(anakinSkywalker, obiwanKonobi);
		} catch (UserNotFoundException | CreateConflictException e) {
			e.printStackTrace();
		}
	}

}
