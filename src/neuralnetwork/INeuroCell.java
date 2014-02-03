/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 * NeuroCell Interface
 * @author moremagic
 */
public interface INeuroCell {
    public void learning(double out, double teachOut, int alfa);
    public void addInputCell(INeuroCell cell);
    public double firing();
}
