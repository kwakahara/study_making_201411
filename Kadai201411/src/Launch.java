import jp.ktsystem.kadai201411.common.KadaiException;
import jp.ktsystem.kadai201411.k_wakahara.Kadai;

public class Launch {
	public static void main(String[] args) {
		final String DirPath = "TestDir//";

		for (int i = 1; i <= 1; i++) {
			try {
				System.out.println("No00"+ i +":"+Kadai.countOrder(DirPath + "/Input"
						+ "/G01T00" + i, DirPath + "/Output" + "/G01T00" + i + "/"));

			} catch (KadaiException e) {
				System.out.println("Error No00"+i+":"+ e.getErrorCode());
			}
		}
/*		
		try {
			System.out.println("No101:"+Kadai.countOrder("", DirPath + "/Output/G01T101/"));

		} catch (KadaiException e) {
			System.out.println("Error No101:"+ e.getErrorCode());
		}
		try {
			System.out.println("No102:"+Kadai.countOrder(null
					+ "/G01T102", DirPath + "/Output/G01T101/"));

		} catch (KadaiException e) {
			System.out.println("Error No102:"+ e.getErrorCode());
		}
		
		for (int i = 3; i <= 9; i++) {
			try {
				System.out.println("No10"+ i +":"+Kadai.countOrder(DirPath + "/Input"
						+ "/G01T10" + i, DirPath + "/Output" + "/G01T10" + i + "/"));

			} catch (KadaiException e) {
				System.out.println("Error No10"+i+":"+ e.getErrorCode());
			}
		}
*/
		for (int i = 10; i <= 19; i++) {
			try {
				System.out.println("No1"+ i +":"+Kadai.countOrder(DirPath + "/Input"
						+ "/G01T1" + i, DirPath + "/Output" + "/G01T1" + i + "/"));

			} catch (KadaiException e) {
				System.out.println("Error No1"+i+":"+ e.getErrorCode());
			}
		}
	}
}
