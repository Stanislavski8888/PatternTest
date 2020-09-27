/**
 * <a href="https://leetcode-cn.com/problems/divisor-game/">1025. 除数博弈</a><br>
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。<br>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：<br>
 * <ul>
 *     <li>选出任一 x，满足 0 < x < N 且 N % x == 0 。</li>
 *     <li>用 N - x 替换黑板上的数字 N 。</li>
 * </ul>
 * 如果玩家无法执行这些操作，就会输掉游戏。<br>
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏。<br>
 *
 * <strong>示例 1：</strong>
 * <pre>
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * </pre>
 * <strong>示例 2：</strong>
 * <pre>
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 * </pre>
 *
 * <strong>提示：</strong>
 * <pre>
 *     1 <= N <= 1000
 * </pre>
 */
public class LeetCode1025 {
    static final int N = (int)(Math.random() * 1000 + 1);
    public static void main(String[] args) {
        System.out.println("N = " + N);
    }

    /**
     * <strong>方法一：找规律</strong><br>
     *
     * <strong>思路与算法</strong><br>
     *
     * 博弈类的问题常常让我们摸不着头脑。当我们没有解题思路的时候，不妨试着写几项试试：<br>
     * <pre>
     * N=1N = 1N=1 的时候，区间 (0,1)(0, 1)(0,1) 中没有整数是 nnn 的因数，所以此时 Alice 败。
     * N=2N = 2N=2 的时候，Alice 只能拿 111，NNN 变成 111，Bob 无法继续操作，故 Alice 胜。
     * N=3N = 3N=3 的时候，Alice 只能拿 111，NNN 变成 222，根据 N=2N = 2N=2 的结论，我们知道此时 Bob 会获胜，Alice 败。
     * N=4N = 4N=4 的时候，Alice 能拿 111 或 222，如果 Alice 拿 111，根据 N=3N = 3N=3 的结论，Bob 会失败，Alice 会获胜。
     * N=5N = 5N=5 的时候，Alice 只能拿 111，根据 N=4N = 4N=4 的结论，Alice 会失败。
     * ......
     * </pre>
     * 写到这里，也许你有了一些猜想。没关系，请大胆地猜想，在这种情况下大胆地猜想是 AC 的第一步。<br>
     * 也许你会发现这样一个现象：NNN 为奇数的时候 Alice（先手）必败，NNN 为偶数的时候 Alice 必胜。<br>
     * 这个猜想是否正确呢？下面我们来想办法证明它。<br>
     *
     * <strong>证明</strong><br>
     * <pre>
     * N=1 和 N=2 时结论成立。
     *
     * N>2 时，假设 N≤k 时该结论成立，则 N=k+1 时：
     * - 如果 k 为偶数，则 k+1 为奇数，x 是 k+1 的因数，只可能是奇数，而奇数减去奇数等于偶数，且 k+1−x ≤ k，故轮到 Bob 的时候都是偶数。
     *   而根据我们的猜想假设 N≤k 的时候偶数的时候先手必胜，故此时无论 Alice 拿走什么，Bob 都会处于必胜态，所以 Alice 处于必败态。
     * - 如果 k 为奇数，则 k+1 为偶数，x 可以是奇数也可以是偶数，若 Alice 减去一个奇数，那么 k+1−x 是一个小于等于 k 的奇数，
     *   此时 Bob 占有它，处于必败态，则 Alice 处于必胜态。
     * </pre>
     * 综上所述，这个猜想是正确的。
     */
    public boolean divisorGame(int N) {
        return false;
    }
}
