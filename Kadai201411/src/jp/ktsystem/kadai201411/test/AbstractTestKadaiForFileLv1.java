package jp.ktsystem.kadai201411.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * <h1>
 *  課題Lv1.用テストケース.
 * </h1>
 *
 * @author KTSystem Ryoichi_Obara
 * @since 2012/10/16
 */
public abstract class AbstractTestKadaiForFileLv1 extends TestCase {

	// --- Public method ---

	@Test
	public void testCaseG01T001() {
		executeValidTest("G01T001", 2);
	}
	@Test
	public void testCaseG01T002() {
		executeValidTest("G01T002", 2);
	}
	@Test
	public void testCaseG01T003() {
		executeValidTest("G01T003", 2);
	}
	@Test
	public void testCaseG01T004() {
		executeValidTest("G01T004", 2);
	}
	@Test
	public void testCaseG01T005() {
		executeValidTest("G01T005", 3);
	}
	@Test
	public void testCaseG01T006() {
		executeValidTest("G01T006", 3);
	}
	@Test
	public void testCaseG01T007() {
		executeValidTest("G01T007", 2);
	}
	/**
	 * 入力ディレクトリパスがnull
	 */
	@Test
	public void testCaseG01T101() {
		String inputPath = null;
		String outputPath = getOutputDirPath() + "G01T101\\";
		executeInvalidTest(inputPath, outputPath, 1);
	}
	/**
	 * 入力ディレクトリが存在しない
	 */
	@Test
	public void testCaseG01T102() {
		String inputPath = getInputDirPath() + "G01T102\\";
		String outputPath = getOutputDirPath() + "G01T102\\";
		executeInvalidTest(inputPath, outputPath, 1);
	}
	/**
	 * 入力ディレクトリの読み取り権限がない
	 */
	@Test
	public void testCaseG01T103() {
		String inputPath = getInputDirPath() + "G01T103\\";
		String outputPath = getOutputDirPath() + "G01T103\\";
		executeInvalidTest(inputPath, outputPath, 1);
	}
	/**
	 * 入力ファイルの読み取り権限がない
	 */
	@Test
	public void testCaseG01T104() {
		String inputPath = getInputDirPath() + "G01T104\\";
		String outputPath = getOutputDirPath() + "G01T104\\";
		executeInvalidTest(inputPath, outputPath, 1);
	}
	/**
	 * 入力ファイルのエンコードがUTF-8以外
	 */
	@Test
	public void testCaseG01T105() {
		String inputPath = getInputDirPath() + "G01T105\\";
		String outputPath = getOutputDirPath() + "G01T105\\";
		executeInvalidTest(inputPath, outputPath, 1);
	}
	/**
	 * 出力ディレクトリパスがnull
	 */
	@Test
	public void testCaseG01T106() {
		String inputPath = getInputDirPath() + "G01T106\\";
		String outputPath = null;
		executeInvalidTest(inputPath, outputPath, 8);
	}

	/**
	 * 出力ディレクトリが存在しない
	 */
	@Test
	public void testCaseG01T107() {
		String inputPath = getInputDirPath() + "G01T107\\";
		String outputPath = getOutputDirPath() + "G01T107\\";
		executeInvalidTest(inputPath, outputPath, 8);
	}
	/**
	 * 出力ディレクトリの読み取り権限がない
	 */
	@Test
	public void testCaseG01T108() {
		String inputPath = getInputDirPath() + "G01T108\\";
		String outputPath = getOutputDirPath() + "G01T108\\";
		executeInvalidTest(inputPath, outputPath, 8);
	}
	/**
	 * 出力ファイルの読み取り権限がない(出力ファイルが既存の場合)
	 */
	@Test
	public void testCaseG01T109() {
		String inputPath = getInputDirPath() + "G01T109\\";
		String outputPath = getOutputDirPath() + "G01T109\\";
		executeInvalidTest(inputPath, outputPath, 8);
	}
	/**
	 * データのカラム数が不正(5項目取得できない)
	 */
	@Test
	public void testCaseG01T110() {
		String inputPath = getInputDirPath() + "G01T110\\";
		String outputPath = getOutputDirPath() + "G01T110\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * データの中に不正文字
	 */
	@Test
	public void testCaseG01T111() {
		String inputPath = getInputDirPath() + "G01T111\\";
		String outputPath = getOutputDirPath() + "G01T111\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 受注IDが空文字
	 */
	@Test
	public void testCaseG01T112() {
		String inputPath = getInputDirPath() + "G01T112\\";
		String outputPath = getOutputDirPath() + "G01T112\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 顧客名が空文字
	 */
	@Test
	public void testCaseG01T113() {
		String inputPath = getInputDirPath() + "G01T113\\";
		String outputPath = getOutputDirPath() + "G01T113\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 製品名が空文字
	 */
	@Test
	public void testCaseG01T114() {
		String inputPath = getInputDirPath() + "G01T114\\";
		String outputPath = getOutputDirPath() + "G01T114\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 数量が空文字
	 */
	@Test
	public void testCaseG01T115() {
		String inputPath = getInputDirPath() + "G01T115\\";
		String outputPath = getOutputDirPath() + "G01T115\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 数量入力値が半角数字でない
	 */
	@Test
	public void testCaseG01T116() {
		String inputPath = getInputDirPath() + "G01T116\\";
		String outputPath = getOutputDirPath() + "G01T116\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 数量入力値が負の整数である
	 */
	@Test
	public void testCaseG01T117() {
		String inputPath = getInputDirPath() + "G01T117\\";
		String outputPath = getOutputDirPath() + "G01T117\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 納期のフォーマットが不正
	 */
	@Test
	public void testCaseG01T118() {
		String inputPath = getInputDirPath() + "G01T118\\";
		String outputPath = getOutputDirPath() + "G01T118\\";
		executeInvalidTest(inputPath, outputPath, 2);
	}
	/**
	 * 数量の計算の結果がオーバーフロー
	 */
	@Test
	public void testCaseG01T119() {
		String inputPath = getInputDirPath() + "G01T119\\";
		String outputPath = getOutputDirPath() + "G01T119\\";
		executeInvalidTest(inputPath, outputPath, 10);
	}

	// --- Protected method ---

	protected void compareToSample(String aFolderName, String aFileName) {
		InputStream sampleStream = null;
		InputStream resultStream = null;

		try {
			sampleStream = createSampleStream(aFolderName, aFileName);
			resultStream = new BufferedInputStream(createResultStream(aFolderName, aFileName));

			compareToSampleStream(sampleStream, resultStream);
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (null != sampleStream) {
				try {
					sampleStream.close();
				} catch (Exception e) {
				}
			}
			if (null != resultStream) {
				try {
					resultStream.close();
				} catch (Exception e) {
				}
			}
		}
	}

	// --- Private method ---

	private InputStream createSampleStream(String aFolderName, String aFileName) throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(getSampleResultDirPath() + aFolderName + "\\" + aFileName));
	}

	private void compareToSampleStream(InputStream aSampleStream, InputStream aResultStream) throws IOException {
		int sample = 0;

		do {
			sample = aSampleStream.read();
			int result = aResultStream.read();
			assertEquals(sample, result);
		} while (0 <= sample);
	}

	private InputStream createResultStream(String aFolderName, String aFileName) throws IOException {

		// バイト順マークを読み飛ばす
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(getOutputDirPath() + aFolderName + "\\" + aFileName);

            int[] headers = new int[] { fs.read(), fs.read(), fs.read() };
            if (headers[0] != 0xEF || headers[1] != 0xBB || headers[2] != 0xBF) {
            	fs.close();
            	fs = null;
            	fs = new FileInputStream(getOutputDirPath() + aFolderName + "\\" + aFileName);
            }
            return fs;
		} catch (IOException e) {
			if (null != fs) {
				try {
				} catch(Exception e2) {}
			}
			throw e;
		}
	}

	// --- Abstract method

	protected abstract void executeValidTest(String aFolderName, int aRecordCount);
	protected abstract void executeInvalidTest(String anInputPath, String anOutputPath, int anErrorCode);

	protected abstract String getInputDirPath();
	protected abstract String getOutputDirPath();
	protected abstract String getSampleResultDirPath();

}
