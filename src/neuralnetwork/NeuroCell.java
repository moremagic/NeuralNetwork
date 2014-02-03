/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ニューロセル クラス
 * @author moremagic
 */
public class NeuroCell implements INeuroCell{
    public static final Random _RND_ = new Random(System.currentTimeMillis());
    private List<INeuroCell> m_inputCellArray = new ArrayList<INeuroCell>();
    private List<Double> m_waitArray = new ArrayList<Double>();
    double m_v = 0;
    
    public NeuroCell(){
        m_v = (2d * _RND_.nextDouble() - 1d);
    }
    
    @Override
    public void addInputCell(INeuroCell cell){
        m_inputCellArray.add(cell);
        m_waitArray.add(2d * _RND_.nextDouble() - 1d);
    }
    
    protected void addInputCell(INeuroCell cell, double w){
        m_inputCellArray.add(cell);
        m_waitArray.add(w);
    }

    /**
     * ニューロセルの発火時 出力計算
     * @return 
     */
    @Override
    public double firing(){
        double[] inputArray = new double[m_inputCellArray.size()];
        double[] waitArray = new double[m_inputCellArray.size()];
        for(int i = 0 ; i < m_inputCellArray.size() ; i++){
            inputArray[i] = m_inputCellArray.get(i).firing();
            waitArray[i] = m_waitArray.get(i);
        }
        
        return NeuroCell.firing(inputArray, waitArray, m_v);
    }
    
    /**
     * 学習
     * @param out 実際の出力
     * @param teachOut 教師値
     */
    @Override
    public void learning(double out, double teachOut, int alfa){
        double d = ((teachOut - out) * out * (1-out));
        for(int i = 0 ; i < m_waitArray.size() ; i++){
            double cal = m_waitArray.get(i) + (alfa * m_inputCellArray.get(i).firing() * d);
            m_waitArray.set(i, cal);
        }
        m_v += alfa *(-1.0)*d;
        
        for(INeuroCell cell : m_inputCellArray){
            cell.firing();
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(this.getClass().getName()).append("\tw :");
        for(double w: m_waitArray){
            sb.append(w);
            sb.append(" ");
        }
        sb.append("\n");
        sb.append("\tv :").append(m_v).append("\n");
        
        for(INeuroCell cell: m_inputCellArray){
            sb.append(cell.toString()).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * ニューロセルの発火時 出力計算
     * シグモイド関数による発火演算
     * 
     * @param inputArray 入力値
     * @param waitArray 荷重係数
     * @param v 閾値
     * @return 出力
     */
    public static double firing(double[] inputArray, double[] waitArray, double v){
        double input = 0d;
        for(int i = 0 ; i < inputArray.length ; i++){
            input += inputArray[i] * waitArray[i];
        }
        input -= v;
        return 1/(1 + Math.exp(-input));
    }
}
