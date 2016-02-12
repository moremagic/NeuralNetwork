/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author moremagic
 */
public class NeuralNetwork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while (!Lerning_test()) {
            //nop
        }
    }

    /**
     * 中間層を作成するユーティリティ
     * @param inputCells 入力となるセルリスト
     * @param size 作成する中間層
     * @return 中間層セルリスト
     */
    public static List<INeuroCell> createCellTree(List<INeuroCell> inputCells, int size) {
        List<INeuroCell> ret = new ArrayList<INeuroCell>();
        for (int i = 0; i < size; i++) {
            NeuroCell middoleCell = new NeuroCell();
            for (INeuroCell in : inputCells) {
                middoleCell.addInputCell(in);
            }
            ret.add(middoleCell);
        }
        return ret;
    }

    public static boolean Lerning_test() {
        boolean ret;
        PlainNeuroCell x1 = new PlainNeuroCell(0d);
        PlainNeuroCell x2 = new PlainNeuroCell(0d);

        NeuroCell rootCell = new NeuroCell();

        //中間層を 3node作る
        for(INeuroCell cell: NeuralNetwork.createCellTree(NeuralNetwork.createCellTree(NeuralNetwork.createCellTree(Arrays.asList(new INeuroCell[]{x1, x2}), 3), 3), 3)){
        //for(INeuroCell cell: NeuralNetwork.createCellTree(NeuralNetwork.createCellTree(Arrays.asList(new INeuroCell[]{x1, x2}), 3), 2)){
        //for(INeuroCell cell: NeuralNetwork.createCellTree(Arrays.asList(new INeuroCell[]{x1, x2}), 3)){
            rootCell.addInputCell(cell);
        }

        double[][] datas = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0},};

        long cnt = 0L;
        double err = Double.MAX_VALUE;

        int ALPHA = 20; //学習係数
        double learnLimit = 0.001d; //目標値

        while (err > learnLimit) {
            err = 0.0d;
            for (int i = 0; i < datas.length; i++) {
                x1.setValue(datas[i][0]);
                x2.setValue(datas[i][1]);
                double ans = rootCell.firing();
                rootCell.learning(ans, datas[i][2], ALPHA);                
                err += Math.pow((ans - datas[i][2]), 2);
            }
            ++cnt;
            if (cnt % 100000 == 0) {
                //計算打ち切り
                //計算に時間がかかりすぎるため再度計算しなおしたほうが良いと判断
//                if (err > 0.3d) {
//                    break;
//                }
                System.out.println("[" + cnt + "]" + err);
            }
        }
        ret = (err < learnLimit);

        //演算終了
        if (!ret) {
            System.out.println("not success: " + err);
        } else {
            System.out.println("success!: " + err);
            for (int i = 0; i < datas.length; i++) {
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
            {1, 1, 1},};

        System.out.println("演算終了");
        for (int i = 0; i < datas.length; i++) {
            x1.setValue(datas[i][0]);
            x2.setValue(datas[i][1]);
            double ans = rootCell.firing();
            System.out.println(datas[i][0] + ":" + datas[i][1] + " -> " + ans + "  [" + datas[i][2] + "]");
        }
    }
}
