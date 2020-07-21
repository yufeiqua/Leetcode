package com.leetcode.DFS.Prunning_DP;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LC0403_FogJump {
    class JumpState {
        int index;
        int step;

        public JumpState(int index, int step) {
            this.index = index;
            this.step = step;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof JumpState)) return false;
            JumpState state = (JumpState) obj;
            return index == state.index && step == state.step;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, step);
        }
    }

    private Map<JumpState, Boolean> memo = new HashMap<>();

    public boolean canCross(int[] stones) {
        return dfs(stones, 0, 0);
    }

    private boolean dfs(int[] stones, int curIndex, int step) {
        if (curIndex >= stones.length - 1) {
            return true;
        }
        Boolean result = memo.get(new JumpState(curIndex, step));
        if (result != null) return result;


        for (int i = curIndex + 1; i < stones.length; i++) {
            if (stones[i] - stones[curIndex] < step) {
                continue;
            } else if (stones[i] - stones[curIndex] > step + 1) {
                break;
            } else {
                if (dfs(stones, i, stones[i] - stones[curIndex])) {
                    memo.put(new JumpState(i, stones[i] - stones[curIndex]), true);
                    return true;
                }
            }
        }
        memo.put(new JumpState(curIndex, step), false);
        return false;
    }
}
