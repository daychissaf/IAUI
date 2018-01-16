package iaui.ia.model;

public enum Direction {

        UP(1, "UP"), DOWN(3, "DOWN"), LEFT(4, "LEFT"), RIGHT(2, "RIGHT"), STOP(0, "STOP");
        int order;
        String name;

        Direction(int order, String name) {
                this.order = order;
                this.name = name;
        }

        public Direction getRandomDirection() {
                int random = (int) Math.floor((Math.random() * 100) % 4);
                return Direction.getDirectionByOrder(random + 1);
        }

        private static Direction getDirectionByOrder(int order) {
                Direction values[] = Direction.values();
                for (Direction direction : values) {
                        if (direction.order == order) {
                                return direction;
                        }
                }
                throw new IllegalArgumentException();
        }

        public int getOrder() {
                return order;
        }

        public Direction getOpposite() {
                switch (this) {
                case UP:
                        return DOWN;
                case DOWN:
                        return UP;
                case LEFT:
                        return RIGHT;
                case RIGHT:
                        return LEFT;
                default:
                        throw new IllegalArgumentException();
                }
        }

        @Override public String toString() {
                return this.name;
        }
}
