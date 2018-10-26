import java.util.Arrays;

public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array = randomGenerateIntArray();
        // bubbleSort(array, array.length);
        // cocktailSort(array, array.length);
        // selectionSort(array, array.length);
        // insertionSort(array, array.length);
        // insertionSortDichotomy(array, array.length);
        // shellSort(array, array.length);

        int[] arrayCopy = Arrays.copyOf(array, array.length);
        mergeSortRecursion(array, 0, array.length);         // 递归实现
        mergeSortIteration(arrayCopy, arrayCopy.length);    // 非递归实现
    }

    static int[] randomGenerateIntArray() {
        int[] randomArray = new int[20];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = 1 + (int) (Math.random() * 100);
        }
        printArray(randomArray);
        return randomArray;
    }

    static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("% 3d%s", A[i], (i == A.length - 1 ? "" : ", "));
        }
        System.out.println();
    }

    static void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 冒泡排序
     * <p>
     * 冒泡排序是一种极其简单的排序算法，也是我所学的第一个排序算法。它重复地走访过要排序的元素，依次比较相邻两个元素，如果他们的顺序错误就把他们调换过来，直到没有元素再需要交换，排序完成。这个算法的名字由来是因为越小(或越大)的元素会经由交换慢慢“浮”到数列的顶端。
     * <p>
     * 冒泡排序算法的运作如下：
     * <ol>
     * <li>比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     * </ol>
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- O(n^2)</li>
     * <li>最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)</li>
     * <li>平均时间复杂度 ---- O(n^2)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 稳定</li>
     */
    static void bubbleSort(int A[], int n) {
        for (int j = 0; j < n - 1; j++) {           // 每次最大元素就像气泡一样"浮"到数组的最后
            for (int i = 0; i < n - 1 - j; i++) {   // 依次比较相邻的两个元素,使较大的那个向后移
                if (A[i] > A[i + 1]) {              // 如果条件改成A[i] >= A[i + 1],则变为不稳定的排序算法
                    swap(A, i, i + 1);
                }
            }
            printArray(A);
        }
    }

    /**
     * 鸡尾酒排序
     * <p>
     * 鸡尾酒排序，也叫定向冒泡排序，是冒泡排序的一种改进。此算法与冒泡排序的不同处在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。
     * 他可以得到比冒泡排序稍微好一点的效能。
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- O(n^2)</li>
     * <li>最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n)</li>
     * <li>平均时间复杂度 ---- O(n^2)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 稳定</li>
     */
    static void cocktailSort(int A[], int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            for (int i = left; i < right; i++) { // 前半轮,将最大元素放到后面
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                }
            }
            right--;
            printArray(A);
            for (int i = right; i > left; i--) { // 后半轮,将最小元素放到前面
                if (A[i - 1] > A[i]) {
                    swap(A, i - 1, i);
                }
            }
            left++;

            printArray(A);
        }
    }

    /**
     * 选择排序
     * <p>
     * 选择排序也是一种简单直观的排序算法。它的工作原理很容易理解：初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；然后，
     * 再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * <p>
     * 注意选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；
     * 而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
     * <p>
     * 选择排序是不稳定的排序算法，不稳定发生在最小元素与A[i]交换的时刻。
     * <p>
     * 比如序列：{ 5, 8, 5, 2, 9 }，一次选择的最小元素是2，然后把2和第一个5进行交换，从而改变了两个元素5的相对次序。
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- O(n^2)</li>
     * <li>最优时间复杂度 ---- O(n^2)</li>
     * <li>平均时间复杂度 ---- O(n^2)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 不稳定</li>
     */
    static void selectionSort(int A[], int n) {
        for (int i = 0; i < n - 1; i++) {       // i为已排序序列的末尾
            int min = i;
            for (int j = i + 1; j < n; j++) {   // 未排序序列
                if (A[j] < A[min]) {            // 找出未排序序列中的最小值
                    min = j;
                }
            }
            if (min != i) {
                swap(A, min, i);                // 放到已排序序列的末尾，该操作很有可能把稳定性打乱，所以选择排序是不稳定的排序算法
            }
            printArray(A);
        }
    }

    /**
     * 插入排序
     * <p>
     * 插入排序是一种简单直观的排序算法。它的工作原理非常类似于我们抓扑克牌。
     * <p>
     * 对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入。
     * <p>
     * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * <p>
     * 具体算法描述如下：
     * <ol>
     * <li>从第一个元素开始，该元素可以认为已经被排序</li>
     * <li>取出下一个元素，在已经排序的元素序列中从后向前扫描</li>
     * <li>如果该元素（已排序）大于新元素，将该元素移到下一位置</li>
     * <li>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>
     * <li>将新元素插入到该位置后</li>
     * <li>重复步骤2~5</li>
     * </ol>
     * <p>
     * 插入排序不适合对于数据量比较大的排序应用。但是，如果需要排序的数据量很小，比如量级小于千，那么插入排序还是一个不错的选择。
     * 插入排序在工业级库中也有着广泛的应用，在STL的sort算法和stdlib的qsort算法中，都将插入排序作为快速排序的补充，用于少量元素的排序（通常为8个或以下）。
     * <li>分类 ------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)</li>
     * <li>最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)</li>
     * <li>平均时间复杂度 ---- O(n^2)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 稳定</li>
     */
    static void insertionSort(int A[], int n) {
        for (int i = 1; i < n; i++) {       // 类似抓扑克牌排序
            int get = A[i];                 // 右手抓到一张扑克牌
            int j = i - 1;                  // 拿在左手上的牌总是排序好的
            while (j >= 0 && A[j] > get) {  // 将抓到的牌与手牌从右向左进行比较
                A[j + 1] = A[j];            // 如果该手牌比抓到的牌大，就将其右移
                j--;
            }
            A[j + 1] = get;                 // 直到该手牌比抓到的牌小(或二者相等)，将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的)
            printArray(A);
        }
    }

    /**
     * 二分插入排序
     * <p>
     * 对于插入排序，如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的次数，我们称为二分插入排序。
     * <p>
     * 当n较大时，二分插入排序的比较次数比直接插入排序的最差情况好得多，但比直接插入排序的最好情况要差，所当以元素初始序列已经接近升序时，直接插入排序比二分插入排序比较次数少。二分插入排序元素移
     * 动次数与直接插入排序相同，依赖于元素初始序列。
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- O(n^2)</li>
     * <li>最优时间复杂度 ---- O(nlogn)</li>
     * <li>平均时间复杂度 ---- O(n^2)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 稳定</li>
     */
    static void insertionSortDichotomy(int A[], int n) {
        for (int i = 1; i < n; i++) {
            int get = A[i];                         // 右手抓到一张扑克牌
            int left = 0;                           // 拿在左手上的牌总是排序好的，所以可以用二分法
            int right = i - 1;                      // 手牌左右边界进行初始化
            while (left <= right) {                 // 采用二分法定位新牌的位置
                int mid = (left + right) / 2;
                if (A[mid] > get) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {   // 将欲插入新牌位置右边的牌整体向右移动一个单位
                A[j + 1] = A[j];
            }
            A[left] = get;                          // 将抓到的牌插入手牌
            printArray(A);
        }
    }

    /**
     * 希尔排序
     * <p>
     * 希尔排序，也叫递减增量排序，是插入排序的一种更高效的改进版本。希尔排序是不稳定的排序算法。
     * <p>
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * <li>插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率</li>
     * <li>但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位</li>
     * <p>
     * 希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是
     * 到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。
     * <p>
     * 假设有一个很小的数据在一个已按升序排好序的数组的末端。如果用复杂度为O(n^2)的排序（冒泡排序或直接插入排序），可能会进行n次的比较和交换才能将该数据移至正确位置。而希尔排序会用较大的步长
     * 移动数据，所以小数据只需进行少数比较和交换即可到正确位置。
     * <p>
     * 希尔排序是不稳定的排序算法，虽然一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱.
     * <p>
     * 比如序列：{ 3, 5, 10, 8, 7, 2, 8, 1, 20, 6 }，h=2时分成两个子序列 { 3, 10, 7, 8, 20 } 和 {
     * 5, 8, 2, 1, 6 } ，未排序之前第二个子序列中的8在前面，现在对两个子序列进行插入排序，得到 { 3, 7, 8, 10, 20 } 和 {
     * 1, 2, 5, 6, 8 } ，即 { 3, 1, 7, 2, 8, 5, 10, 6, 20, 8 } ，两个8的相对次序发生了改变。
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- 根据步长序列的不同而不同。已知最好的为O(n(logn)^2)</li>
     * <li>最优时间复杂度 ---- O(n)</li>
     * <li>平均时间复杂度 ---- 根据步长序列的不同而不同。</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 不稳定</li>
     */
    static void shellSort(int[] A, int n) {
        int h = 0;
        while (h <= n) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j = i - h;
                int get = A[i];
                while (j >= 0 && A[j] > get) {
                    A[j + h] = A[j];
                    j = j - h;
                }
                A[j + h] = get;
                printArray(A);
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 归并排序
     * <p>
     * 归并排序是创建在归并操作上的一种有效的排序算法，效率为O(nlogn)，1945年由冯·诺伊曼首次提出。
     * <p>
     * 归并排序的实现分为递归实现与非递归(迭代)实现。递归实现的归并排序是算法设计中分治策略的典型应用，我们将一个大问题分割成小问题分别解决，然后用所有小问题的答案来解决整个大问题。非递归(迭代)
     * 实现的归并排序首先进行是两两归并，然后四四归并，然后是八八归并，一直下去直到归并了整个数组。
     * <p>
     * 归并排序算法主要依赖归并(Merge)操作。归并操作指的是将两个已经排序的序列合并成一个序列的操作，归并操作步骤如下：
     * <ol>
<li>   * <li>申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>
     * <li>设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>
     * <li>比较两个指针所指向的元素，选择相对小<li>素放入到合并空间，并移动指针到下一位置</li>
     * <li>重复步骤3直到某一指针到达序列尾</li>
     * <li>将另一序列剩下的所有元素直接
     * <li>到合并序列尾</li>
     * </ol>
     * 
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 -<li>------- 数组</li>
     * <li>最差时间复杂度 ---- O(nlogn)</li>
     * <li>最优时间复杂度---- O(nlogn)</li>
     * <li>平均时间复杂度 ---- O(nlogn)</li>
     * <li>所需辅助空间 ------ O(n)</li>
     * <li>稳定性 ------------ 稳定</li>
     * 
     */
    static void merge(int A[], int left, int mid, int right) {  // 合并两个已排好序的数组A[left...mid]和A[mid+1...right]
        int len = right - left + 1;
        int[] temp = new int[len];                              // 辅助空间O(n)
        int index = 0;
        int i = left;                                           // 前一数组的起始元素
        int j = mid + 1;                                        // 后一数组的起始元素
        while (i <= mid && j <= right) {
            temp[index++] = A[i] <= A[j] ? A[i++] : A[j++];     // 带等号保证归并排序的稳定性
        }

        while (i <= mid) {
            temp[index++] = A[i++];
        }

        while (j <= right) {
            temp[index++] = A[j++];
        }

        for (int k = 0; k < len; k++) {
            A[left++] = temp[k];
        }
    }

    /**
     * 递归实现的归并排序(自顶向下)
     */
    static void mergeSortRecursion(int A[], int left, int right) {
        if (left == right)      // 当待排序的序列长度为1时，递归开始回溯，进行merge操作
            return;
        int mid = (left + right) / 2;
        mergeSortRecursion(A, left, mid);
        mergeSortRecursion(A, mid + 1, right);
        merge(A, left, mid, right);
    }

    /**
     * 非递归(迭代)实现的归并排序(自底向上)
     */
    static void mergeSortIteration(int[] A, int len) {
        int left, mid, right;                           // 子数组索引,前一个为A[left...mid]，后一个子数组为A[mid+1...right]
        for (int i = 0; i < len; i *= 2) {              // 子数组的大小i初始为1，每轮翻倍
            left = 0;
            while (left + i < len) {                    // 后一个子数组存在(需要归并)
                mid = left + i - 1;
                right = mid + i < len ? mid + i : len - 1;// 后一个子数组大小可能不够
                merge(A, left, mid, right);
                left = right + 1;                       // 前一个子数组索引向后移动
            }
        }
    }

    /**
     * <h1>堆排序</h1>
     * <p>
     * 堆排序是指利用堆这种数据结构所设计的一种选择排序算法。堆是一种近似完全二叉树的结构（通常堆是通过一维数组来实现的），并满足性质：以最大堆（也叫大根堆、大顶堆）为例，
     * 其中父结点的值总是大于它的孩子节点。
     * <p>
     * 我们可以很容易的定义堆排序的过程：
     * <ol>
     * <li>由输入的无序数组构造一个最大堆，作为初始的无序区</li>
     * <li>把堆顶元素（最大值）和堆尾元素互换</li>
     * <li>把堆（无序区）的尺寸缩小1，并调用heapify(A, 0)从新的堆顶元素开始进行堆调整</li>
     * <li>重复步骤2，直到堆的尺寸为1</li>
     * </ol>
     * <p>
     * 堆排序是不稳定的排序算法，不稳定发生在堆顶元素与A[i]交换的时刻。
     * <p>
     * 比如序列：{ 9, 5, 7, 5 }，堆顶元素是9，堆排序下一步将9和第二个5进行交换，得到序列 { 5, 5, 7, 9 }，再进行堆调整得到{ 7,
     * 5, 5, 9 }，重复之前的操作最后得到{ 5, 5, 7, 9 }从而改变了两个5的相对次序。
     * 
     * <li>分类 -------------- 内部比较排序</li>
     * <li>数据结构 ---------- 数组</li>
     * <li>最差时间复杂度 ---- O(nlogn)</li>
     * <li>最优时间复杂度 ---- O(nlogn)</li>
     * <li>平均时间复杂度 ---- O(nlogn)</li>
     * <li>所需辅助空间 ------ O(1)</li>
     * <li>稳定性 ------------ 不稳定</li>
     * 
     */
    static void heapify(int A[], int i, int size) // 从A[i]向下进行堆调整
    {
        int left_child = 2 * i + 1; // 左孩子索引
        int right_child = 2 * i + 2; // 右孩子索引
        int max = i; // 选出当前结点与其左右孩子三者之中的最大值
        if (left_child < size && A[left_child] > A[max])
            max = left_child;
        if (right_child < size && A[right_child] > A[max])
            max = right_child;
        if (max != i) {
            swap(A, i, max); // 把当前结点和它的最大(直接)子节点进行交换
            heapify(A, max, size); // 递归调用，继续从当前结点向下进行堆调整
        }
    }

    static int buildHeap(int A[], int n) // 建堆，时间复杂度O(n)
    {
        int heap_size = n;
        for (int i = heap_size / 2 - 1; i >= 0; i--) // 从每一个非叶结点开始向下进行堆调整
            heapify(A, i, heap_size);
        return heap_size;
    }

    static void heapSort(int A[], int n) {
        int heap_size = buildHeap(A, n); // 建立一个最大堆
        while (heap_size > 1) // 堆（无序区）元素个数大于1，未完成排序
        {
            // 将堆顶元素与堆的最后一个元素互换，并从堆中去掉最后一个元素
            // 此处交换操作很有可能把后面元素的稳定性打乱，所以堆排序是不稳定的排序算法
            swap(A, 0, --heap_size);
            heapify(A, 0, heap_size); // 从新的堆顶元素开始向下进行堆调整，时间复杂度O(logn)
        }
    }

    /**
     * <h1>快速排序</h1>
     * <p>
     * 快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序n个元素要O(nlogn)次比较。在最坏状况下则需要O(n^2)次比较，但这种状况并不常见。事实上，
     * 快速排序通常明显比其他O(nlogn)算法更快，因为它的内部循环可以在大部分的架构上很有效率地被实现出来。
     * <p>
     * 快速排序使用分治策略(Divide and Conquer)来把一个序列分为两个子序列。步骤为：
     * <ol>
     * <li>从序列中挑出一个元素，作为"基准"(pivot).</li>
     * <li>把所有比基准值小的元素放在基准前面，所有比基准值大的元素放在基准的后面（相同的数可以到任一边），这个称为分区(partition)操作。</li>
     * <li>对每个分区递归地进行步骤1~2，递归的结束条件是序列的大小是0或1，这时整体已经被排好序了。</li>
     * </ol>
     * <p>
     * <li>分类 ------------ 内部比较排序.</li>
     * <li>数据结构 --------- 数组.</li>
     * <li>最差时间复杂度 -----
     * 每次选取的基准都是最大（或最小）的元素，导致每次只划分出了一个分区，需要进行n-1次划分才能结束递归，时间复杂度为O(n^2).</li>
     * <li>最优时间复杂度 ----
     * 每次选取的基准都是中位数，这样每次都均匀的划分出两个分区，只需要logn次划分就能结束递归，时间复杂度为O(nlogn).</li>
     * <li>平均时间复杂度 ---- O(nlogn).</li>
     * <li>所需辅助空间 ------
     * 主要是递归造成的栈空间的使用(用来保存left和right等局部变量)，取决于递归树的深度，一般为O(logn)，最差为O(n).</li>
     * <li>稳定性 ---------- 不稳定.</li>
     */
    static int partition(int A[], int left, int right) // 划分函数
    {
        int pivot = A[right]; // 这里每次都选择最后一个元素作为基准
        int tail = left - 1; // tail为小于基准的子数组最后一个元素的索引
        for (int i = left; i < right; i++) // 遍历基准以外的其他元素
        {
            if (A[i] <= pivot) // 把小于等于基准的元素放到前一个子数组末尾
            {
                swap(A, ++tail, i);
            }
        }
        swap(A, tail + 1, right); // 最后把基准放到前一个子数组的后边，剩下的子数组既是大于基准的子数组
                                  // 该操作很有可能把后面元素的稳定性打乱，所以快速排序是不稳定的排序算法
        return tail + 1; // 返回基准的索引
    }

    static void quickSort(int A[], int left, int right) {
        if (left >= right)
            return;
        int pivot_index = partition(A, left, right); // 基准的索引
        quickSort(A, left, pivot_index - 1);
        quickSort(A, pivot_index + 1, right);
    }
}
