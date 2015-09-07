/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/7A3351129A/AndroidStudioProjects/BService/app/src/main/aidl/youten/redo/bservice/BServiceListener.aidl
 */
package youten.redo.bservice;
public interface BServiceListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements youten.redo.bservice.BServiceListener
{
private static final java.lang.String DESCRIPTOR = "youten.redo.bservice.BServiceListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an youten.redo.bservice.BServiceListener interface,
 * generating a proxy if needed.
 */
public static youten.redo.bservice.BServiceListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof youten.redo.bservice.BServiceListener))) {
return ((youten.redo.bservice.BServiceListener)iin);
}
return new youten.redo.bservice.BServiceListener.Stub.Proxy(obj);
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
youten.redo.bservice.event.BEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = youten.redo.bservice.event.BEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.onEvent(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements youten.redo.bservice.BServiceListener
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
@Override public boolean onEvent(youten.redo.bservice.event.BEvent event) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((event!=null)) {
_data.writeInt(1);
event.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onEvent, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_onEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public boolean onEvent(youten.redo.bservice.event.BEvent event) throws android.os.RemoteException;
}
