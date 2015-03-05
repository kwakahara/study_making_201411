package jp.ktsystem.kadai201411.k_wakahara;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ktsystem.kadai201411.common.ErrorCode;
import jp.ktsystem.kadai201411.common.KadaiException;
import jp.ktsystem.kadai201411.k_wakahara.KadaiUtil;

/**
 * フォルダの中身のtxtファイルを リストとして取得するクラス
 * 
 * @author k_wakahara
 *
 */

public class CheckAndReadFile {
	/**
	 * エンコード
	 */
	private final static String ENCODING = "UTF-8";
	/**
	 * 拡張子
	 */
	private final static String SUFFIX = "txt";

	/**
	 * ファイル名の頭の文字列
	 */
	private final static String FILR_NAME_TOP = "order";

	/**
	 * 
	 * @param str
	 * @return
	 */
	private static FilenameFilter getFileRegexFilter(String str) {
		final String stri = str;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean ret = name.matches(stri);
				return ret;
			}
		};
	}

	/**
	 * フォルダの中身をチェックするメソッド
	 * 
	 * @param anOrderFileDir
	 *            チェックするフォルダパス
	 * @return フォルダ内のテキストファイルの中身のリスト
	 * @throws KadaiException
	 */
	public static List<List<String>> checkFiles(String anOrderFileDir)
			throws KadaiException {

		// 引数nullチェック
		if (null == anOrderFileDir) {
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}

		File dir = new File(anOrderFileDir);
		File[] files = dir.listFiles(getFileRegexFilter(FILR_NAME_TOP + ".*."
				+ SUFFIX));

		List<List<String>> returnTextList = new ArrayList<List<String>>();
		if (null == files) {
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}

		try {
			for (int i = 0; i < files.length; i++) {
				if(!files[i].isDirectory()){
					// フォルダの中身をチェック
					String filePath = files[i].toString();
	
					// ファイルの拡張子が「txt」の場合のみ読みこみ処理を実行
					returnTextList.add(KadaiUtil.readFile(filePath, ENCODING));
				}
			}
		} catch (IOException e) {
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}

		return returnTextList;
	}
}
