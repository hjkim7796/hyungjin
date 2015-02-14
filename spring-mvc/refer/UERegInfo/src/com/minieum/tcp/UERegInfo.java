package com.minieum.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

/*

			 enum UEInfoType{
    ESNType = 'E',
    MEIDType = 'M'
};



#define UEIMSI  "000000000000000"
#define UEESN   "00000000"
#define UEMEID  "00000000000000"
#define UEMAC   "000000000000"

typedef struct UERegInfo{
    char idType;
    unsigned char esn[ESN_LENG] ;
    unsigned char meid[MEID_LENG] ;
    unsigned char imsi[IMSI_LENG] ;
    unsigned char macId[MAC_LENG] ;
    unsigned char gpsTime[GPS_TIME_LENG] ;
}UERegInfo_T;


 */
public class UERegInfo implements Comparator<UERegInfo>, Serializable {
	private static final long serialVersionUID = -6272480276523291916L;
	//From UE
	final static byte ESNType='E';
	final static byte MEIDType='M';
	final static byte IType='I';
	
	//From Client
	public final static byte GetList='#';
	public final static byte Clear='*';
	public final static byte Ack='\0';
	
	final static int ID_TYPE_LENG=1;
	final static int LOCAL_TIME_LENG=50;
	final static int ESN_LENG=9;
	final static int MEID_LENG=15;
	final static int IMSI_LENG=16;
	final static int MAC_LENG=13;
	final static int GPS_TIME_LENG=50;
	
	static SimpleDateFormat dateFormat= new SimpleDateFormat("MM/d/yyyy HH:mm:ss");
	
	transient int ordering = 0;
	
	public final static int INDEX_TIME=0;
	public final static int INDEX_ESN=1;
	public final static int INDEX_MEID=2;
	public final static int INDEX_IMSI=3;
	public final static int INDEX_MAC=4;
	public final static int INDEX_GPSTIME=5;
	public final static int INDEX_RIP=6;
	public final static int INDEX_IMSI_MAC=7;
	//public final static int INDEX_CLEAR=7;

	byte idType;
	long localTime;
	String remoteAddress;
	
	byte esn[] = new byte[ESN_LENG];
	byte meid[] = new byte[MEID_LENG];
	byte imsi[] = new byte[IMSI_LENG];
	byte macId[] = new byte[MAC_LENG];
	byte gpsTime[] = new byte[GPS_TIME_LENG];
	
	public UERegInfo() {
		
	}
	
	public UERegInfo(int ordering) {
		this.ordering = ordering;
	}
	
	public byte getIdType() {
		return idType;
	}
	
	public long getLocalTime() {
		return localTime;
	}

	public byte[] getEsn() {
		return esn;
	}

	public byte[] getMeid() {
		return meid;
	}

	public byte[] getImsi() {
		return imsi;
	}

	public byte[] getMacId() {
		return macId;
	}

	public byte[] getGpsTime() {
		return gpsTime;
	}
	
	public String getIdTypeStr() {
		return String.valueOf((char)idType);
	}
	
	public String getLocalTimeStr() {
		return dateFormat.format(new Date(localTime));
	}
	
	public String getRemoteAddressStr() {
		return remoteAddress;
	}
	
	public String getEsnStr() {
		return new String(esn);
	}

	public String getMeidStr() {
		return new String(meid);
	}

	public String getImsiStr() {
		return new String(imsi);
	}

	public String getMacIdStr() {
		return new String(macId);
	}

	public String getGpsTimeStr() {
		return new String(gpsTime);
	}

	public void setIdType(byte idType) {
		this.idType = idType;
	}
	
	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public static UERegInfo decoding(InputStream in) throws Exception {
		byte c = (byte)in.read();
		
		if( c == -1 )
			throw new RuntimeException("EOF");
		
		UERegInfo result = new UERegInfo();
		result.idType = c;
		
		if( c == GetList)
			return result;
			
		result.localTime = new Date().getTime();
		
		in.read(result.esn,0, ESN_LENG);
		in.read(result.meid,0, MEID_LENG);
		in.read(result.imsi,0, IMSI_LENG);
		in.read(result.macId,0, MAC_LENG);
		in.read(result.gpsTime,0, GPS_TIME_LENG);
		
		return result;
	}
	
	public static UERegInfo getDefaultValue() {
		UERegInfo value = new UERegInfo();
		int i;
		Random r = new Random();
		
		value.idType = ESNType;
		for(i=0; i <LOCAL_TIME_LENG ; i++)
			value.localTime = new Date().getTime();

		for(i=0; i <ESN_LENG ; i++) {
			Integer v = r.nextInt(9);
			value.esn[i] = (byte)(v.toString().charAt(0));
		}
		for(i=0; i <MEID_LENG ; i++) {
			Integer v = r.nextInt(9);
			value.meid[i] = (byte)(v.toString().charAt(0));
		}
		for(i=0; i <IMSI_LENG ; i++) {
			Integer v = r.nextInt(9);
			value.imsi[i] = (byte)(v.toString().charAt(0));
		}
		for(i=0; i <MAC_LENG ; i++) {
			Integer v = r.nextInt(9);
			value.macId[i] = (byte)(v.toString().charAt(0));
		}
		for(i=0; i <MAC_LENG ; i++) {
			Integer v = r.nextInt(9);
			value.gpsTime[i] = (byte)(v.toString().charAt(0));
		}

		return value;
	}
	
	/**
	 * Convert the int to an byte array.
	 * @param integer The integer
	 * @return The byte array
	 */
	public static byte[] intToByteArray(final int integer) {

	    ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE / 8);
	    buff.putInt(integer);
	    buff.order(ByteOrder.BIG_ENDIAN);
	    return buff.array();
	}

	/**
	* Convert the byte array to an int.
	*
	* @param bytes The byte array
	* @return The integer
	*/
	public static int byteArrayToInt(byte[] bytes) {

	    final int size = Integer.SIZE / 8;
	    ByteBuffer buff = ByteBuffer.allocate(size);
	    final byte[] newBytes = new byte[size];
	    for (int i = 0; i < size; i++) {
	        if (i + bytes.length < size) {
	            newBytes[i] = (byte) 0x00;
	        } else {
	            newBytes[i] = bytes[i + bytes.length - size];
	        }
	    }
	    buff = ByteBuffer.wrap(newBytes);
	    buff.order(ByteOrder.BIG_ENDIAN);
	    return buff.getInt();
	}
	
	public void writes(OutputStream out) throws Exception {
		out.write((byte)idType);
		out.write(esn,0, ESN_LENG);
		out.write(meid,0,MEID_LENG);
		out.write(imsi,0,IMSI_LENG);
		out.write(macId,0,MAC_LENG);
		out.write(gpsTime,0,GPS_TIME_LENG);
		out.flush();
	}
	
	@Override
	public int compare(UERegInfo o2, UERegInfo o1) {
		if(o1 == o2)
			return 0;
		switch(ordering) {
			case INDEX_TIME :
				//return o1.getLocalTimeStr().compareTo(o2.getLocalTimeStr());
				return new Long(o1.getLocalTime()).compareTo(new Long(o2.getLocalTime()));
			case INDEX_ESN :
				return o1.getEsnStr().compareTo(o2.getEsnStr());
			case INDEX_MEID :
				return o1.getMeidStr().compareTo(o2.getMeidStr());
			case INDEX_IMSI :
				return o1.getImsiStr().compareTo(o2.getImsiStr());
			case INDEX_MAC :
				return o1.getMacIdStr().compareTo(o2.getMacIdStr());
			case INDEX_GPSTIME :
				return o1.getGpsTimeStr().compareTo(o2.getGpsTimeStr());
			case INDEX_RIP :
				return o1.getRemoteAddressStr().compareTo(o2.getRemoteAddressStr());
			case INDEX_IMSI_MAC :
				return (o1.getMacIdStr() + o1.getImsiStr() ).compareTo(o2.getMacIdStr() + o2.getImsiStr() );
			default:
				return o1.getLocalTimeStr().compareTo(o2.getLocalTimeStr());
		}
	}
	
	
	@Override
	public int hashCode() {
		if(ordering == INDEX_IMSI_MAC) {
			return (getMacIdStr() + getImsiStr() ).hashCode();
		} else
			return super.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(ordering == INDEX_IMSI_MAC) {
			UERegInfo another = (UERegInfo)o;
			return (getMacIdStr() + getImsiStr()).equals( another.getMacIdStr() + another.getImsiStr() );
		} else
			return super.equals(o);
	}

	public String toString() {
		String msg = "\n-------------------------------------";
		msg += "\n\t idType : " + idType;
		msg += "\n\t localTime : " + getLocalTimeStr();
		msg += "\n\t esn : " + getEsnStr();
		msg += "\n\t meid : " + getMeidStr();
		msg += "\n\t imsi : " + getImsiStr();
		msg += "\n\t macId : " + getMacIdStr();
		msg += "\n\t gpsTime : " + getGpsTimeStr();
		msg += "\n\t remoteAddress : " + getRemoteAddressStr();
		msg += "\n-------------------------------------";
		return msg;
	}
}
