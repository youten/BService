/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/7A3351129A/AndroidStudioProjects/BService/app/src/main/aidl/youten/redo/bservice/BServiceIF.aidl
 */
package youten.redo.bservice;
public interface BServiceIF extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements youten.redo.bservice.BServiceIF
{
private static final java.lang.String DESCRIPTOR = "youten.redo.bservice.BServiceIF";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an youten.redo.bservice.BServiceIF interface,
 * generating a proxy if needed.
 */
public static youten.redo.bservice.BServiceIF asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof youten.redo.bservice.BServiceIF))) {
return ((youten.redo.bservice.BServiceIF)iin);
}
return new youten.redo.bservice.BServiceIF.Stub.Proxy(obj);
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
case TRANSACTION_registerListener:
{
data.enforceInterface(DESCRIPTOR);
youten.redo.bservice.BServiceListener _arg0;
_arg0 = youten.redo.bservice.BServiceListener.Stub.asInterface(data.readStrongBinder());
this.registerListener(_arg0);
return true;
}
case TRANSACTION_unregisterListener:
{
data.enforceInterface(DESCRIPTOR);
youten.redo.bservice.BServiceListener _arg0;
_arg0 = youten.redo.bservice.BServiceListener.Stub.asInterface(data.readStrongBinder());
this.unregisterListener(_arg0);
return true;
}
case TRANSACTION_sendCommand:
{
data.enforceInterface(DESCRIPTOR);
youten.redo.bservice.event.BEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = youten.redo.bservice.event.BEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.sendCommand(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements youten.redo.bservice.BServiceIF
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
@Override public void registerListener(youten.redo.bservice.BServiceListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerListener, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterListener(youten.redo.bservice.BServiceListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterListener, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void sendCommand(youten.redo.bservice.event.BEvent command) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((command!=null)) {
_data.writeInt(1);
command.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_sendCommand, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_registerListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_sendCommand = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void registerListener(youten.redo.bservice.BServiceListener listener) throws android.os.RemoteException;
public void unregisterListener(youten.redo.bservice.BServiceListener listener) throws android.os.RemoteException;
public void sendCommand(youten.redo.bservice.event.BEvent command) throws android.os.RemoteException;
}
