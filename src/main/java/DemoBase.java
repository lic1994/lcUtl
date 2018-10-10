
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foeris.y.common.jdbc.JdbcUtl;
import com.foeris.y.spring.utils.SpringUtl;
import com.foeris.y.test.spring.SpringDemoBase;
import com.wxzd.wcs_manage.domain.DeviceDomain;
import com.wxzd.wcs_manage.dto.DeviceManageDto;
import com.wxzd.wcs_manage.enums.DeviceType;
import com.wxzd.wcs_manage.repository.DeviceRepository;

/**
 * 
 * @ContextConfiguration 继承配置
 * 
 * @version 1
 * @author y
 * @.create 2016-09-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config-custom-db.xml" })
public class DemoBase extends SpringDemoBase {

	@Test
	public void show() {
		SpringUtl.showAllBeanName(applicationContext);
	}

	@Before
	public void before() {
		System.out.println(".......");
		JdbcUtl.sqlFormatLogOpen();
		JdbcUtl.sqlLogOpen();
		JdbcUtl.sqlNamedLogOpen();
	}

	@Autowired
	DeviceRepository deviceRepository;

	@Test
	public void test1005001() throws Exception {
		ExcelRead test = new ExcelRead();
		ArrayList<ArrayList<String>> arr = test.xlsx_reader("F:/1.xlsx", 0);

		for (int i = 0; i < arr.size(); i++) {
			ArrayList<String> row = arr.get(i);
			for (String s : row)

			{
				System.out.println(s);
				DeviceDomain domain = new DeviceDomain();
				DeviceRepository deviceRepository = new DeviceRepository();
				domain.setName(s);
				domain.setCode(s);
				domain.setType(DeviceType.conveyor);
				deviceRepository.saveById(domain);
			}

		}

		List<DeviceManageDto> devices = deviceRepository.queryAllDto();
		for (DeviceManageDto device : devices) {
			System.out.println(device.getName());
		}
	}
}
