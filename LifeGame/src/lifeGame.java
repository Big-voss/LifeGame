import java.util.Scanner;

public class lifeGame {
    static final int ROWLEN = 10, COLLEN = 10, DEAD = 0, ALIVE = 1;
    static int[][] cell = new int[ROWLEN][COLLEN];
    //定义临时二维空间数组存储变化的状态:
    static int[][] celltemp = new int[ROWLEN][COLLEN];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("生命游戏开始: ");
        //二维空间中放置细胞初始化数量
        initCell();
        //显示初始状态
        showCell();
        //是否进行下一次演化
        String flag;
        do {
            //开始生命游戏
            startCell();
            System.out.println("是否进行下一次演化: y/n");
            flag = input.next();
            showCell();
        } while (flag.equalsIgnoreCase("y"));


    }


    private static void initCell() {
        int row, col;
        Scanner input = new Scanner(System.in);
        for (row = 0; row < 10; row++) {
            for (col = 0; col < 10; col++) {
                cell[row][col] = DEAD;//全部初始化为死状态
            }
        }
        System.out.println("请输入一组活细胞的坐标位置,输入(-1,-1)代表结束");
        while (true) {
            System.out.println("请输入一组活细胞的坐标位置:");
            row = input.nextInt();
            col = input.nextInt();
            if (0 <= row && row < ROWLEN && 0 <= col && col < COLLEN) {
                cell[row][col] = ALIVE;
            } else if (row == -1 && col == -1) {
                break;
            } else {
                System.out.println("输入坐标超过范围");
            }
        }
    }


    private static void startCell() {
        int row, col, sum, count = 0;
        for (row = 0; row < ROWLEN; row++) {
            for (col = 0; col < COLLEN; col++) {
                //每个单元格的细胞周围活着的细胞
                switch (sumCell(row, col)) {
                    case 2:
                        celltemp[row][col] = cell[row][col];
                        break;
                    case 3:
                        celltemp[row][col] = ALIVE;
                        break;
                    default:
                        celltemp[row][col] = DEAD;
                }
            }
        }

        //temp放回cell
        for (row = 0; row < ROWLEN; row++) {
            for (col = 0; col < COLLEN; col++) {
                cell[row][col] = celltemp[row][col];
                if (celltemp[row][col] == ALIVE) {
                    count++;//演化后当前存活的细胞数量
                }
            }
        }
        showCell();
        System.out.println("当前状态下存活的细胞数量为:" + count);
    }

    //统计四周的细胞个数
    static int sumCell(int row, int col) {
        int count = 0;
        int c, r;//上一行,下一行,左边列,右边列

        for (r = row - 1; r < row + 1; r++) {//从上一行循环到下一行
            for (c = col - 1; c < col + 1; c++) {
                if (r < 0 || r >= ROWLEN || c < 0 || c >= COLLEN) {
                    continue;
                }
                if (cell[r][c] == ALIVE) {
                    count++;
                }
            }
        }
        if (cell[row][col] == ALIVE) {
            count--;

        }
        return count;
    }

    //统计四周的细胞个数

    private static void showCell() {
        int row, col;
        System.out.printf("\n细胞状态\n");
        System.out.printf("┌-");//第一行的左上角
        for (col = 0; col < COLLEN - 2; col++) {//第一行
            System.out.printf("-┬-");
        }
        System.out.printf("┐\n");//第一行的右上角

        for (row = 0; row < ROWLEN; row++) {
            System.out.printf("|");//行的最左边的线
            for (col = 0; col < COLLEN; col++) {//各单元格细胞的生成状态
                switch (cell[row][col]) {
                    case ALIVE:
                        System.out.printf("●|");
                        break;
                    case DEAD:
                        System.out.printf("○|");
                        break;
                }
            }
            System.out.println();//换行
            //输出行与行之间的水平分割线:
            if (row < ROWLEN - 1) {
                System.out.printf("├-");
                for (col = 0; col < COLLEN - 2; col++) {
                    System.out.printf("-┼-");//输出一行水平分割线
                }
                System.out.printf("┤\n");
            }

        }
        //输出最后一行:
        System.out.printf("└-");
        for (col = 0; col < COLLEN -2 ; col++) {
            System.out.printf("-┴-");
        }
        System.out.printf("┘\n");
    }
}
