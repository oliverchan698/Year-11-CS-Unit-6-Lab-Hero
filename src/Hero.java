public class Hero {
        private String name;
        private int hitPoints;

        public Hero(String name) {
            this.name = name;
            this.hitPoints = 100;
        }

        public String getName() {
            return name;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public void attack(Hero opponent) {
            double determiner = Math.random();
            if (determiner < 0.5) {
                opponent.hitPoints -= 10;
            } else {
                this.hitPoints -= 10;
            }
        }

        public void senzuBean() {
            this.hitPoints = 100;
        }

        private void fightUntilTheDeathHelper(Hero opponent, boolean wait) {
            while (true) {
                this.attack(opponent);
                if (wait) {
                    System.out.println(this.name + ": " + this.hitPoints + "     " + opponent.name + ": " + opponent.hitPoints);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (this.hitPoints <= 0 || opponent.hitPoints <= 0) {
                    break;
                }
            }
        }

        public String fightUntilTheDeath(Hero opponent, boolean wait) {
            this.senzuBean();
            opponent.senzuBean();
            this.fightUntilTheDeathHelper(opponent, wait);
            return this.name + ": " + this.hitPoints + "     " + opponent.name + ": " + opponent.hitPoints;
        }

        private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
            int[] results = new int[2];
            int heroWins = 0;
            int opponentWins = 0;
            for (int i = 0; i < n; i++) {
                this.fightUntilTheDeathHelper(opponent, false);
                if (this.hitPoints > 0) {
                    heroWins++;
                } else {
                    opponentWins++;
                }
                this.senzuBean();
                opponent.senzuBean();
            }
            results[0] = heroWins;
            results[1] = opponentWins;
            return results;
        }

        public String nFightsToTheDeath(Hero opponent, int n) {
            int[] results = this.nFightsToTheDeathHelper(opponent, n);
            String returnString = this.name + ": " + results[0] + " wins\n" + opponent.name + ": " + results[1] + " wins\n";
            if (results[0] > results[1]) {
                returnString = returnString + this.name + " wins!";
            } else if (results[1] > results[0]) {
                returnString = returnString + opponent.name + " wins!";
            } else if (results[0] == results[1]) {
                returnString = returnString + "OMG! It was actually a draw!";
            }
            return returnString;
        }

        public void dramaticFightToTheDeath(Hero opponent) {
            this.senzuBean();
            opponent.senzuBean();
            this.fightUntilTheDeath(opponent, true);
            if (this.hitPoints > 0) {
                System.out.println(this.name + " wins!");
            } else {
                System.out.println(opponent.name + " wins!");
            }
        }
    }
