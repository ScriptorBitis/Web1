package org.example;

public class ServerMath {

    public ServerMath() {
    }

    public boolean checkResults(String x, String y, String r) {
        if (x == null || y == null || r == null) {
            return false;
        }
        try {
            float checkVar = Float.parseFloat(x);
            checkVar = Integer.parseInt(y);
            checkVar = Integer.parseInt(r);
        } catch (NumberFormatException e) {
            return false;
        }
        float xInt = Float.parseFloat(x);
        if (xInt < -3 || xInt > 3) {
            return false;
        }
        int yInt = Integer.parseInt(y);
        if (yInt < -2 || yInt > 2) {
            return false;
        }
        int rInt = Integer.parseInt(r);
        if (rInt < 1 || rInt > 5) {
            return false;
        }

        return true;
    }

    public boolean checkHit(String x, String y, String r) {
        float xFlt = Float.parseFloat(x);
        int yInt = Integer.parseInt(y);
        int rInt = Integer.parseInt(r);


        float distance = (float) Math.sqrt(xFlt * xFlt + yInt * yInt);

        //Прям в 4 четверти
        if (xFlt > 0 & yInt < 0) {
            return (xFlt <= rInt) &
                    (yInt <= rInt/2f);
        }

        // четверть круга в 1 четверти
        if (xFlt > 0 & yInt > 0) {
            return distance <= rInt;
        }
        //треуг в 2 четверти
        if (xFlt < 0 & yInt > 0) {
            return (yInt<= rInt/2f + xFlt) &
                    (Math.abs(xFlt) <= rInt/2f) &
                    (Math.abs(yInt) <= rInt/2f);
        }


        return false;
    }

}
