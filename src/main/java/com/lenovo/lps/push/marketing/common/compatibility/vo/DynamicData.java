package com.lenovo.lps.push.marketing.common.compatibility.vo;

public class DynamicData {
	private CellInfo cellInfo;
	private DataAccessInfo dataAccessInfo;
	private StateChange stateChange;

	public CellInfo getCellInfo() {
		return cellInfo;
	}

	public void setCellInfo(CellInfo cellInfo) {
		this.cellInfo = cellInfo;
	}

	public DataAccessInfo getDataAccessInfo() {
		return dataAccessInfo;
	}

	public void setDataAccessInfo(DataAccessInfo dataAccessInfo) {
		this.dataAccessInfo = dataAccessInfo;
	}

	public StateChange getStateChange() {
		return stateChange;
	}

	public void setStateChange(StateChange stateChange) {
		this.stateChange = stateChange;
	}

	// CellInfo
	public static class CellInfo {
		private String systemID;
		private String locationID;
		private String cellID;
		private String latitude;
		private String longitude;

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			CellInfo c = (CellInfo) o;
			if ((this.systemID == c.getSystemID() || this.systemID.equals(c
					.getSystemID()))
					&& (this.locationID == c.getLocationID() || this.locationID
							.equals(c.getLocationID()))
					&& (this.cellID == c.getCellID() || this.cellID.equals(c
							.getCellID()))
					&& (this.latitude == c.getLatitude() || this.latitude
							.equals(c.getLatitude()))
					&& (this.longitude == c.getLongitude() || this.longitude
							.equals(c.getLongitude()))) {
				return true;
			}
			return false;
		}

		public String getSystemID() {
			return systemID;
		}

		public void setSystemID(String systemID) {
			this.systemID = systemID;
		}

		public String getLocationID() {
			return locationID;
		}

		public void setLocationID(String locationID) {
			this.locationID = locationID;
		}

		public String getCellID() {
			return cellID;
		}

		public void setCellID(String cellID) {
			this.cellID = cellID;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
	}

	// DataAccessInfo
	public static class DataAccessInfo {
		private String networkMode;
		private String ip;
		private String apn;
		private String operatorCode;
		private String imsi;

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			DataAccessInfo d = (DataAccessInfo) o;

			if ((this.networkMode == d.getNetworkMode() || this.networkMode
					.equals(d.getNetworkMode()))
					&& (this.ip == d.getIp() || this.ip.equals(d.getIp()))
					&& (this.apn == d.getApn() || this.apn.equals(d.getApn()))
					&& (this.operatorCode == d.getOperatorCode() || this.operatorCode
							.equals(d.getOperatorCode()))
					&& (this.imsi == d.getImsi() || this.imsi.equals(d
							.getImsi()))) {
				return true;
			}
			return false;
		}

		public String getNetworkMode() {
			return networkMode;
		}

		public void setNetworkMode(String networkMode) {
			this.networkMode = networkMode;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getApn() {
			return apn;
		}

		public void setApn(String apn) {
			this.apn = apn;
		}

		public String getOperatorCode() {
			return operatorCode;
		}

		public void setOperatorCode(String operatorCode) {
			this.operatorCode = operatorCode;
		}

		public String getImsi() {
			return imsi;
		}

		public void setImsi(String imsi) {
			this.imsi = imsi;
		}

	}

	// StateChange
	public static class StateChange {
		private boolean batteryState;
		
		
		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			StateChange stateChange = (StateChange) o;
			if (this.batteryState == stateChange.isBatteryState()) {
				return true;
			}
			return false;
		}

		public boolean isBatteryState() {
			return batteryState;
		}

		public void setBatteryState(boolean batteryState) {
			this.batteryState = batteryState;
		}
	}

}
