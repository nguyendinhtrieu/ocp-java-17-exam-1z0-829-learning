```
var raf = new RandomAccessFile("c:\\temp\\test.txt", "rwd");
```
- "r": Open for reading only. Invoking any of the write methods of the resulting object will cause an IOException to be thrown.
- "rw": Open for reading and writing. If the file does not already exist then an attempt will be made to create it.
- "rws": Open for reading and writing, as with "rw", and also require that every update to the file's content or metadata be written synchronously to the underlying storage device.
- "rwd": Open for reading and writing, as with "rw", and also require that every update to the file's content be written synchronously to the underlying storage device.
