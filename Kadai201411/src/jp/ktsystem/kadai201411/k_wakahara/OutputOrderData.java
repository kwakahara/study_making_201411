package jp.ktsystem.kadai201411.k_wakahara;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.ktsystem.kadai201411.common.ErrorCode;
import jp.ktsystem.kadai201411.common.KadaiException;

/**
 * ファイルに出力するクラス
 * 
 * @author wakahara
 *
 */
public class OutputOrderData {
	/**
	 * 出力ファイル名
	 */
	public final static String OUTPUT_FILE_NAME = "ordercount.out";

	/**
	 * データをファイルに出力するクラス
	 * 
	 * @param anOutputDir
	 *            フォルダパス
	 * @param anOrdersDataMap
	 *            　出力用データ
	 * @return　出力数
	 * @throws KadaiException
	 */
	public static int outputTextFile(String anOutputDir,
			Map<String, String> anOrdersDataMap) throws KadaiException {
		int count = 0;

		if(null == anOutputDir){
			throw new KadaiException(ErrorCode.QUESTION1_FILE_OUTPUT);
		}
		if(null == anOrdersDataMap){
			throw new KadaiException(ErrorCode.SALES_ORDER_FILE_INPUT);
		}
		
		List<Map.Entry<String, String>> entries = sortMap(anOrdersDataMap);
		
		try(BufferedWriter bw= new BufferedWriter(new FileWriter(anOutputDir
				+ OUTPUT_FILE_NAME));) {
			
			for (Map.Entry<String, String> data : entries) {
				bw.write(data.getKey() + "," + data.getValue());
				bw.newLine();
				++count;
			}
			
			bw.flush();
		} catch (IOException e) {
			throw new KadaiException(ErrorCode.QUESTION1_FILE_OUTPUT);
		}

		return count;
	}

	/**
	 * MAPのソートメソッド
	 * 
	 * @param aSortMap
	 *            ソートするマップ
	 */
	public static List<Map.Entry<String, String>> sortMap(Map<String, String> aSortMap) {
		List<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(
				aSortMap.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> entry1,
					Entry<String, String> entry2) {
				int num = entry1.getValue().compareTo(entry2.getValue());
				System.out.println(num);
				return num;
			}
		});
		
		return entries;

	}
}
