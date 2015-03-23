package jp.ktsystem.kadai201411.test;



import jp.ktsystem.kadai201411.common.KadaiException;
import jp.ktsystem.kadai201411.k_wakahara.Kadai;
import junit.framework.Assert;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 課題テストクラス(Lv1)
 * @author Y.Kimura
 */
@RunWith(JUnit4.class)
public class KadaiTest201411_Lv1 extends AbstractTestKadaiForFileLv1{

/*	*//**
	 * @param anInputPath
	 * @param aScore
	 *//*
	private void assertEquals(long aRecordCount) {

		try {
			Assert.assertEquals(aRecordCount, Kadai.countOrder(INPUT_FILE_DIRECTORY, OUTPUT_FILE_DIRECTORY));
		} catch (KadaiException e) {
			throw new RuntimeException(e);
		}
	}


	*//**
	 * 異常動作確認
	 * @param aStartTime 勤務開始時間
	 * @param aEndTime 勤務終了時間
	 * @param expecteds throwされるエラーコード
	 *//*
	private void assertFail(String anInputPath, ErrorCode anErrorCode) {

		try {
			Kadai.countOrder(INPUT_FILE_DIRECTORY, OUTPUT_FILE_DIRECTORY);
			Assert.fail();
		} catch (KadaiException e) {
			Assert.assertEquals(anErrorCode.getErrorCode(), e.getErrorCode());
		}
	}*/

	@Override
	protected void executeValidTest(String aFolderName, int aRecordCount) {
		//try {
			Assert.assertEquals(aRecordCount, Kadai.countOrder(getInputDirPath() + aFolderName + "\\", getOutputDirPath() + aFolderName + "\\"));
			compareToSample(aFolderName, "ordercount.txt");
		//} catch (KadaiException e) {
			//throw new RuntimeException(e);
		//}

	}

	@Override
	protected void executeInvalidTest(String anInputPath, String anOutputPath, int anErrorCode) {
		//try {
			Kadai.countOrder(anInputPath, anOutputPath);
			fail("なぜ成功する？");
		//} catch (KadaiException e) {
			//assertEquals(anErrorCode, e.getErrorCode());
		//}
	}

	@Override
	protected String getInputDirPath() {
		return "C:\\studymarking\\201411\\TestDir\\Input\\";
	}

	@Override
	protected String getOutputDirPath() {
		return "C:\\studymarking\\201411\\TestDir\\Output\\";
	}

	@Override
	protected String getSampleResultDirPath() {
		return "C:\\studymarking\\201411\\TestDir\\Sample\\";
	}

}
