/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 * 値を指定するための NeuroCell
 * @author moremagic
 */
public class NeuroCellAsignete extends NeuroCell{
    public NeuroCellAsignete(double v){
        m_v = v;
    }
    
    @Override
    public void addInputCell(INeuroCell cell, double w){
        super.addInputCell(cell, w);
    }
}
