package jp.ktsystem.kadai201411.k_wakahara;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 課題のUtilクラス
 * 
 * @author wakahara
 *
 */
public class KadaiUtil {

	/**
	 * ファイルの読み込みメソッド
	 * 
	 * @param aFilePath
	 *            : ファイルパス
	 * @param anEncoding
	 *            : エンコード
	 * @return : ファイルの1行分を1要素としたリスト
	 * @throws IOException
	 */
	public static List<String> readFile(String aFilePath, String anEncoding)
			throws IOException {

		// 引数nullチェック
		if (null == aFilePath) {
			throw new FileNotFoundException();
		}
		if (null == anEncoding) {
			throw new IOException();
		}

		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(aFilePath);
			InputStream is = inputStream;

			// BOMチェック
			if (!is.markSupported()) {
				// マーク機能が無い場合BufferedInputStreamを被せる
				is = new BufferedInputStream(inputStream);
			}
			is.mark(3); // 先頭にマークを付ける
			if (3 <= is.available()) {
				// BOMチェック
				byte b[] = { 0, 0, 0 };
				if(!isUTF8(b, anEncoding)){
					throw new IOException();
				}
				is.read(b, 0, 3);
				if ((byte) 0xEF != b[0] || (byte) 0xBB != b[1]
						|| (byte) 0xBF != b[2]) {
					// BOMでない場合は先頭まで巻き戻す
					is.reset();
				}
			}

			// 以下ファイルを読み込み、リストに格納する処理
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(is, anEncoding));

			// ファイルを読み込み後に格納し、RETURNする
			List<String> textList = new ArrayList<String>();
			String str;
			while (null != (str = fileReader.readLine())) {
				textList.add(str);
			}

			fileReader.close();

			return textList;

		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				throw new IOException();
			}
		}
	}
	
	/**
	 * UTF-8かどうかを判定するメソッド
	 * @param src
	 * @param encode
	 * @return　UTF-8ならTRUE
	 */
	private static boolean isUTF8(byte[] src ,String encode){
		if(null == src || null == encode){
			return false;
		}
		try{
			byte[] testEncoding = new String(src, encode).getBytes(encode);
			return Arrays.equals(testEncoding, src);
		}catch(UnsupportedEncodingException e){
			return false;
		} 
	}
}
