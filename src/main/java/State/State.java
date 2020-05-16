package State;

import java.util.ArrayList;

public class State {
    private ArrayList<Integer> state;
    private boolean isover;

    public State() {
        isover = false;
        this.state = new ArrayList();
        state.add(10);
    }

    public boolean isGameOver() {
        for (int heap:state) {
            if(heap > 2) return false;
        }
        isover = true;
        return true;
    }

    public boolean crackHeap(int heapNum, int val1, int val2){
        if((heapNum < state.size()) && (heapNum > 0) && (val1 > 0) && (val2 > 0) && (val1+val2 == state.get(heapNum)) && (val1 != val2)){
            state.set(heapNum, val1);
            if (heapNum == state.size() - 1) {
                state.add(val2);
            } else {
                state.add(heapNum + 1, val2);
            }
            isGameOver();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "state=" + state;
    }
}
