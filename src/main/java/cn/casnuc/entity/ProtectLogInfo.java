package cn.casnuc.entity;

public class ProtectLogInfo {
	
	private int id;
	private String time;
	private String fileName;
	private int transferDrection;
	private int serviceType;
	private String sourceIp;
	private String destinationIp;
	private String sourceMac;
	private String destinationMac;
	private int sourcePort;
	private int destinationPort;
	private int transferResult;
	private int fileTypeCheck;
	private int fileFormCheck;
	private int keyWordCheck;
	private String remarks;
		
	public String getDestinationMac() {
		return destinationMac;
	}
	public void setDestinationMac(String destinationMac) {
		this.destinationMac = destinationMac;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getTransferDrection() {
		return transferDrection;
	}
	public void setTransferDrection(int transferDrection) {
		this.transferDrection = transferDrection;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	public String getSourceMac() {
		return sourceMac;
	}
	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}
	public int getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}
	public int getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
	}
	public int getTransferResult() {
		return transferResult;
	}
	public void setTransferResult(int transferResult) {	
		this.transferResult = transferResult;		
	}
	public int getFileTypeCheck() {
		return fileTypeCheck;
	}
	public void setFileTypeCheck(int fileTypeCheck) {
		this.fileTypeCheck = fileTypeCheck;
	}
	public int getFileFormCheck() {
		return fileFormCheck;
	}
	public void setFileFormCheck(int fileFormCheck) {
		this.fileFormCheck = fileFormCheck;
	}
	public int getKeyWordCheck() {
		return keyWordCheck;
	}
	public void setKeyWordCheck(int keyWordCheck) {
		this.keyWordCheck = keyWordCheck;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
