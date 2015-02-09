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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 課題のUtilクラス
 * @author wakahara
 *
 */
public class KadaiUtil {

	/**
	 * ファイルの読み込みメソッド
	 * @param aFilePath : ファイルパス
	 * @param anEncoding : エンコード
	 * @return : ファイルの1行分を1要素としたリスト
	 * @throws IOException
	 */
	public static List<String> readFile(String aFilePath, String anEncoding) throws IOException{
		
		// 引数nullチェック
		if(null == aFilePath){
			throw new FileNotFoundException();
		}
		if(null == anEncoding){
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

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		}finally{
			try{
				if(null != inputStream){
					inputStream.close();
				}
			}catch (IOException e){
				throw new IOException();
			}
		}
	}

	/**
	 * ファイル名から拡張子が指定のものかどうかをチェックするメソッド
	 * @param aFileName	：ファイル名
	 * @param aSuffix	：指定拡張子
	 * @return 拡張子が指定のものならばTrue,それ以外はFalse
	 */
	public static boolean checkSuffix(String aFileName, String aSuffix) {
		// ファイル名nullチェック
		if (null == aFileName) {
			return false;
		}
		// 拡張子名nullチェック
		if (null == aSuffix){
			return false;
		}
		//判定するパターンを生成
        Pattern ptn = Pattern.compile(".*"+aSuffix+"$");
        Matcher match = ptn.matcher(aFileName);

		return match.find();
	}
	
	/**
	 * 指定された文字コードかどうかの判定
	 * @param src
	 * @return
	 */
	public static boolean checkEncoding(byte[] src, String anEncoding){
		if(null == src || null == anEncoding){
			return false;
		}
		
        try {
            byte[] tmp = new String(src, anEncoding).getBytes(anEncoding);
            return Arrays.equals(tmp, src);
        }
        catch(UnsupportedEncodingException e) {
            return false;
        }
    }


}
