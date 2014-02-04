/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 *
 * @author moremagic
 */
public class NeuralNetwork {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(!Lerning_test()){
            //nop
        }
    }
    
    public static boolean Lerning_test() {
        boolean ret;
        PlainNeuroCell x1 = new PlainNeuroCell(0d);
        PlainNeuroCell x2 = new PlainNeuroCell(0d);
        
        NeuroCell middoleCell_1 = new NeuroCell();
        middoleCell_1.addInputCell(x1);
        middoleCell_1.addInputCell(x2);
        
        NeuroCell middoleCell_2 = new NeuroCell();
        middoleCell_2.addInputCell(x1);
        middoleCell_2.addInputCell(x2);
        
        NeuroCell middoleCell_3 = new NeuroCell();
        middoleCell_3.addInputCell(x1);
        middoleCell_3.addInputCell(x2);
        
        NeuroCell rootCell = new NeuroCell();
        rootCell.addInputCell(middoleCell_1);
        rootCell.addInputCell(middoleCell_2);
        rootCell.addInputCell(middoleCell_3);
        
        double[][] datas = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0},
        };
        
        long cnt = 0L;
        double err = Double.MAX_VALUE;
        while(err > 0.001d){
            err = 0.0d;
            for(int i = 0 ; i < datas.length ; i++){
                x1.setValue(datas[i][0]);
                x2.setValue(datas[i][1]);           
                double ans = rootCell.firing();             
                rootCell.learning(ans, datas[i][2], 10);
                err += Math.pow((ans - datas[i][2]), 2);
            }
            ++cnt;
            if(cnt % 100000 ==0){
                if(err > 0.3d)break;
                System.out.println("[" + cnt + "]" + err);
            }
        }
        ret = ( err < 0.001d );
        
        //演算終了
        if(!ret){
            System.out.println("not success: " + err);
        }else{
            System.out.println("success!: " + err);
            for(int i = 0 ; i < datas.length ; i++){
                x1.setValue(datas[i][0]);
                x2.setValue(datas[i][1]);
                double ans = rootCell.firing();
                System.out.println(datas[i][0] + ":" + datas[i][1] + " -> " + ans + "  [" + datas[i][2] + "]");
            }
            System.out.println("root_cell_1: \n" + rootCell.toString());
        }
        
        return ret;
    }
    
    
    public static void OrNeuroLerning() {
        PlainNeuroCell x1 = new PlainNeuroCell(0d);
        PlainNeuroCell x2 = new PlainNeuroCell(0d);
        
        NeuroCell middoleCell_1 = new NeuroCellAsignete(1d);
        middoleCell_1.addInputCell(x1, -1d);
        middoleCell_1.addInputCell(x2, -3d);
        
        NeuroCell middoleCell_2 = new NeuroCellAsignete(-2d);
        middoleCell_2.addInputCell(x1, 3d);
        middoleCell_2.addInputCell(x2, 1d);
        
        NeuroCell rootCell = new NeuroCellAsignete(2);
        rootCell.addInputCell(middoleCell_1, -27);
        rootCell.addInputCell(middoleCell_2, 8);
        
        double[][] datas = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
        };
        
        System.out.println("演算終了");
        for(int i = 0 ; i < datas.length ; i++){
            x1.setValue(datas[i][0]);
            x2.setValue(datas[i][1]);
            double ans = rootCell.firing();
            System.out.println(datas[i][0] + ":" + datas[i][1] + " -> " + ans + "  [" + datas[i][2] + "]");
        }
    }
}
