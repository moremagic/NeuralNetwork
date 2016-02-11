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
    /**
     * 学習
     * @param out 出力値
     * @param teachOut 教師値
     * @param alfa 学習係数 
     */
    public void learning(double out, double teachOut, int alfa);
    
    /**
     * 次段セルを結合する
     * @param cell 次段セル
     */
    public void addInputCell(INeuroCell cell);
    
    /**
     * 発火させる
     * @return 結果
     */
    public double firing();
}
