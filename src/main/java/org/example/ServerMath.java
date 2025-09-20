package org.example;

public class ServerMath {

    public ServerMath() {
    }

    public boolean checkResults(String x, String y, String r) {
        if (x == null || y == null || r == null) {
            return false;
        }
        try {
            float checkVarX = Float.parseFloat(x);
            float checkVarY = Float.parseFloat(y);
            int checkVarR = Integer.parseInt(r);
        } catch (NumberFormatException e) {
            return false;
        }
        float xInt = Float.parseFloat(x);
        if (xInt < -3 || xInt > 3) {
            return false;
        }
        float yInt = Float.parseFloat(y);
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
        float yFlt = Float.parseFloat(y);
        int rInt = Integer.parseInt(r);


        float distance = (float) Math.sqrt(xFlt * xFlt + yFlt * yFlt);

        //Прям в 4 четверти
        if (xFlt >= 0 & yFlt <= 0) {
            return (xFlt <= rInt) &
                    (Math.abs(yFlt) <= rInt/2f);
        }

        // четверть круга в 1 четверти
        if (xFlt >= 0 & yFlt >= 0) {
            return distance <= rInt;
        }
        //треуг в 2 четверти
        if (xFlt <= 0 & yFlt >= 0) {
            return (yFlt <= rInt/2f + xFlt) &
                    (Math.abs(xFlt) <= rInt/2f) &
                    (Math.abs(yFlt) <= rInt/2f);
        }


        return false;
    }

}
