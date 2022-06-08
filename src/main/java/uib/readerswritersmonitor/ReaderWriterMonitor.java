package uib.readerswritersmonitor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderWriterMonitor {
    
    public static void main(String[] args) {
        
    }
    
}

class RWMonitor{
    volatile int readers = 0;
    volatile boolean writing = false;
    
    synchronized void readerLock(){
        while(writing){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RWMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        readers++;
        this.notifyAll();
    }
    
    synchronized void readerUnlock(){
        readers--;
        if(readers == 0){
            notifyAll();
        }
    }
    
    synchronized void writerLock(){
        while(writing || readers != 0){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RWMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    synchronized void writerUnlock(){
        writing = false;
        notifyAll();
    }
}
