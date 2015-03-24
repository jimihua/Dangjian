/**   
 *    
 * 项目名称：Dangjian     
 * 方法描述:   
 * 创建人：Administrator   
 * 创建时间：2015-3-24 下午12:54:34   
 * 修改人：Administrator   
 * 修改时间：2015-3-24 下午12:54:34   
 * 修改备注：   
 * @version    
 *    
 */
package com.star.model;

/**
 * 方法描述:
 * 
 * @param
 * @author KIMI 创建时间：2015-3-24 下午12:54:34
 * @version
 * 
 */
public class News {

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private String title;
	private String imgsrc;
	private String link;
}
