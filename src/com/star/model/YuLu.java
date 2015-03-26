/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-26 下午4:54:11   
 * 修改人：Administrator   
 * 修改时间：2015-3-26 下午4:54:11   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * 方法描述:
 * 
 * @param 语录
 * @author KIMI 创建时间：2015-3-26 下午4:54:16
 * @version
 * 
 */
@Table(name = "xidada")
public class YuLu {
	@Id(column = "id")
	private int id;
	private String cls;
	private String origin;
	private String word;
	private String situation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
}
