/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\eclipse372\\workspace\\BService\\src\\com\\example\\bservice\\BServiceListener.aidl
 */
package com.example.bservice;
public interface BServiceListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.bservice.BServiceListener
{
private static final java.lang.String DESCRIPTOR = "com.example.bservice.BServiceListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.bservice.BServiceListener interface,
 * generating a proxy if needed.
 */
public static com.example.bservice.BServiceListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.bservice.BServiceListener))) {
return ((com.example.bservice.BServiceListener)iin);
}
return new com.example.bservice.BServiceListener.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onEvent:
{
data.enforceInterface(DESCRIPTOR);
com.example.bservice.event.BEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.bservice.event.BEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onEvent(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.bservice.BServiceListener
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void onEvent(com.example.bservice.event.BEvent event) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((event!=null)) {
_data.writeInt(1);
event.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onEvent(com.example.bservice.event.BEvent event) throws android.os.RemoteException;
}
