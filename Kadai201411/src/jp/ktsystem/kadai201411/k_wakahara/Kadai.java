package jp.ktsystem.kadai201411.k_wakahara;

import java.util.List;
import java.util.Map;

import jp.ktsystem.kadai201411.common.KadaiException;

/**
 * 課題の仕様通りのクラス
 * 
 * @author k_wakahara
 * @since 2014/12/13
 * */
public class Kadai {

	/**
	 * 
	 * @param anOrderFileDir
	 *            　読み込みフォルダ
	 * @param anOutputDir
	 *            　出力フォルダ
	 * @return
	 */
	public static int countOrder(String anOrderFileDir, String anOutputDir) {
		try {
			// 読み取り
			List<List<String>> textList = CheckAndReadFile
					.checkFiles(anOrderFileDir);
			// 集計
			Map<String, String> totalizationDataMap = TotalizationOrder
					.totalizationOrderData(textList);

			// 出力
			int outputFileNumber = OutputOrderData.outputTextFile(anOutputDir,
					totalizationDataMap);

			return outputFileNumber;
		} catch (KadaiException e) {
			return e.getErrorCode();
		}
	}
}