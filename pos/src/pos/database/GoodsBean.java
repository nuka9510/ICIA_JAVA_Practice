package pos.database;

import java.util.ArrayList;

public class GoodsBean {

	private String request;
	private String goodsCode;
	private String goodsName;
	private int goodsPrice;
	private int goodsAmount;
	private int goodsStock;
	private int goodsSafetyStock;
	private String goodsExpireDate;
	private String saleDate;
	private String[][] saleInfoList;
	private ArrayList<GoodsBean> refundList;
	private String state;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	public int getGoodsSafetyStock() {
		return goodsSafetyStock;
	}
	public void setGoodsSafetyStock(int goodsSafetyStock) {
		this.goodsSafetyStock = goodsSafetyStock;
	}
	public String getGoodsExpireDate() {
		return goodsExpireDate;
	}
	public void setGoodsExpireDate(String goodsExpireDate) {
		this.goodsExpireDate = goodsExpireDate;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public String[][] getSaleInfoList() {
		return saleInfoList;
	}
	public void setSaleInfoList(String[][] saleInfoList) {
		this.saleInfoList = saleInfoList;
	}
	public ArrayList<GoodsBean> getRefundList() {
		return refundList;
	}
	public void setRefundList(ArrayList<GoodsBean> refundList) {
		this.refundList = refundList;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
