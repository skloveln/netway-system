package cn.casnuc.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataQuery {
	private String start;
	private String end;
	private String fileName;
	private String transferDrection;
	private String transferResult;
	private String sourceIp;
	private String sourceMac;
	private String destinationIp;
	private String destinationMac;
	
	public String getStart() {		
		return start;
	}	
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {	
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getTransferDrection() {
		if(transferDrection.equals("单向导入")){
			return 2;
		}else if(transferDrection.equals("单向导出")){
			return 1;
		}else{
			return 999;
		}
	}
	public void setTransferDrection(String transferDrection) {
		this.transferDrection = transferDrection;
	}
	public int getTransferResult() {
		if(transferResult.equals("成功")){
			return 0;
		}else if(transferResult.equals("失败")){
			return 1;
		}else{
			return 999;
		}		
	}
	public void setTransferResult(String transferResult) {
		this.transferResult = transferResult;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getSourceMac() {
		return sourceMac;
	}
	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	public String getDestinationMac() {
		return destinationMac;
	}
	public void setDestinationMac(String destinationMac) {
		this.destinationMac = destinationMac;
	}
	
	public Date dateConvertor(String value) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date result = null;		
		try {
			result = dateFormat.parse(value);
		} catch (ParseException e) {
			System.out.println(value + "  时间转换异常");
		}
		
		return result;
	}	
}
