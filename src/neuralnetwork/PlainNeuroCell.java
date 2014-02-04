/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 * データ入力用の Cell
 * @author moremagic
 */
public class PlainNeuroCell implements INeuroCell{
    private double value = 0d;
    public PlainNeuroCell(double value){
        this.value = value;
    }
    
    public void setValue(double value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(this.getClass().getName()).append("\tw :");
        sb.append("\t").append(value);
        return sb.toString();
    }
        
    @Override
    public void addInputCell(INeuroCell cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double firing() {
        return value;
    }

    @Override
    public void learning(double out, double teachOut, int alfa) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
