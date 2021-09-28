package com.srpago.gasoline.manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.srpago.gasoline.manager.utils.FuelType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"_id",
	"calle",
	"rfc",
	"date_insert",
	"regular",
	"colonia",
	"numeropermiso",
	"fechaaplicacion",
	"\ufeffpermisoid",
	"longitude",
	"latitude",
	"premium",
	"razonsocial",
	"codigopostal",
	"dieasel"
})
@Entity
public class FuelStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("_id")
	private String fuelStationId;
	
	@JsonProperty("calle")
	private String calle;
	
	@JsonProperty("rfc")
	private String rfc;
	
	@JsonProperty("date_insert")
	private String dateInsert;
	
	@JsonProperty("regular")
	private String regular;
	
	@JsonProperty("colonia")
	private String colonia;
	
	@JsonProperty("numeropermiso")
	private String numeropermiso;
	
	@JsonProperty("fechaaplicacion")
	private String fechaaplicacion;
	
	@JsonProperty("\ufeffpermisoid")
	private String permisoid;
	
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("premium")
	private String premium;
	
	@JsonProperty("razonsocial")
	private String razonsocial;
	
	@JsonProperty("codigopostal")
	private String codigopostal;
	
	@JsonProperty("dieasel")
	private String dieasel;
	
	// adding missed fuel type
	@JsonProperty("magna")
	private String magna;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("_id")
	public String getFuelStationId() {
		return fuelStationId;
	}
	
	@JsonProperty("_id")
	public void setFuelStationId(String fuelStationId) {
		this.fuelStationId = fuelStationId;
	}
	
	@JsonProperty("calle")
	public String getCalle() {
		return calle;
	}
	
	@JsonProperty("calle")
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	@JsonProperty("rfc")
	public String getRfc() {
		return rfc;
	}
	
	@JsonProperty("rfc")
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	@JsonProperty("date_insert")
	public String getDateInsert() {
		return dateInsert;
	}
	
	@JsonProperty("date_insert")
	public void setDateInsert(String dateInsert) {
		this.dateInsert = dateInsert;
	}
	
	@JsonProperty("regular")
	public String getRegular() {
		return regular;
	}
	
	@JsonProperty("regular")
	public void setRegular(String regular) {
		this.regular = regular;
	}
	
	@JsonProperty("colonia")
	public String getColonia() {
		return colonia;
	}
	
	@JsonProperty("colonia")
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	@JsonProperty("numeropermiso")
	public String getNumeropermiso() {
		return numeropermiso;
	}
	
	@JsonProperty("numeropermiso")
	public void setNumeropermiso(String numeropermiso) {
		this.numeropermiso = numeropermiso;
	}
	
	@JsonProperty("fechaaplicacion")
	public String getFechaaplicacion() {
		return fechaaplicacion;
	}
	
	@JsonProperty("fechaaplicacion")
	public void setFechaaplicacion(String fechaaplicacion) {
		this.fechaaplicacion = fechaaplicacion;
	}
	
	@JsonProperty("\ufeffpermisoid")
	public String getPermisoid() {
		return permisoid;
	}
	
	@JsonProperty("\ufeffpermisoid")
	public void setPermisoid(String permisoid) {
		this.permisoid = permisoid;
	}
	
	@JsonProperty("longitude")
	public String getLongitude() {
		return longitude;
	}
	
	@JsonProperty("longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@JsonProperty("latitude")
	public String getLatitude() {
		return latitude;
	}
	
	@JsonProperty("latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@JsonProperty("premium")
	public String getPremium() {
		return premium;
	}
	
	@JsonProperty("premium")
	public void setPremium(String premium) {
		this.premium = premium;
	}
	
	@JsonProperty("razonsocial")
	public String getRazonsocial() {
		return razonsocial;
	}
	
	@JsonProperty("razonsocial")
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	
	@JsonProperty("codigopostal")
	public String getCodigopostal() {
		return codigopostal;
	}
	
	@JsonProperty("codigopostal")
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	
	@JsonProperty("dieasel")
	public String getDieasel() {
		return dieasel;
	}
	
	@JsonProperty("dieasel")
	public void setDieasel(String dieasel) {
		this.dieasel = dieasel;
	}

	public String getMagna() {
		return magna;
	}

	public void setMagna(String magna) {
		this.magna = magna;
	}
	
	public String getFuel(FuelType fuelType) {
		String fuel = null;
		switch (fuelType) {
		case MAGNA:
			fuel = this.getMagna();
			break;
		case PREMIUM:
			fuel = this.getPremium();
		default:
			break;
		}
		return fuel;
	}
	
	

}
