package api.brainsynder.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.Random;

public class UtilMath {
    public static final Random random = new Random(System.nanoTime());
    public static final float nanoToSec = 1.0E-9F;
    public static final float FLOAT_ROUNDING_ERROR = 1.0E-6F;
    public static final float PI = 3.1415927F;
    public static final float PI2 = 6.2831855F;
    public static final float SQRT_3 = 1.73205F;
    public static final float E = 2.7182817F;
    public static final float radiansToDegrees = 57.295776F;
    public static final float radDeg = 57.295776F;
    public static final float degreesToRadians = 0.017453292F;
    public static final float degRad = 0.017453292F;
    static final int ATAN2_DIM = (int) Math.sqrt(16384.0D);
    private static final int SIN_BITS = 14;
    private static final int SIN_MASK = 16383;
    private static final int SIN_COUNT = 16384;
    private static final float radFull = 6.2831855F;
    private static final float degFull = 360.0F;
    private static final float radToIndex = 2607.5945F;
    private static final float degToIndex = 45.511112F;
    private static final int ATAN2_BITS = 7;
    private static final int ATAN2_BITS2 = 14;
    private static final int ATAN2_MASK = 16383;
    private static final int ATAN2_COUNT = 16384;
    private static final float INV_ATAN2_DIM_MINUS_1;
    private static final int BIG_ENOUGH_INT = 16384;
    private static final double BIG_ENOUGH_FLOOR = 16384.0D;
    private static final double CEIL = 0.9999999D;
    private static final double BIG_ENOUGH_CEIL = 16384.999999999996D;
    private static final double BIG_ENOUGH_ROUND = 16384.5D;

    static {
        INV_ATAN2_DIM_MINUS_1 = 1.0F / (float) (ATAN2_DIM - 1);
    }

    public UtilMath() {
    }

    public static int getRandomNumberBetween(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        return randomNumber == min ? min + 1 : randomNumber;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }

    public static int randRange(int min, int max) {
        Random r = new Random();
        return min + r.nextInt() * (max - min);
    }

    public static double randomRange(double min, double max) {
        return Math.random() < 0.5D ? (1.0D - Math.random()) * (max - min) + min : Math.random() * (max - min) + min;
    }

    public static float randomRangeFloat(float min, float max) {
        return (float) (Math.random() < 0.5D ? (1.0D - Math.random()) * (double) (max - min) + (double) min : Math.random() * (double) (max - min) + (double) min);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static final Vector rotateAroundAxisX(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static final Vector rotateAroundAxisZ(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    public static int randomRangeInt(int min, int max) {
        return (int) (Math.random() < 0.5D ? (1.0D - Math.random()) * (double) (max - min) + (double) min : Math.random() * (double) (max - min) + (double) min);
    }

    public static Vector getRandomVector() {
        double x = random.nextDouble() * 2.0D - 1.0D;
        double y = random.nextDouble() * 2.0D - 1.0D;
        double z = random.nextDouble() * 2.0D - 1.0D;
        return (new Vector(x, y, z)).normalize();
    }

    public static Vector getRandomVectorline() {
        byte minz = 1;
        byte maxz = 3;
        int rz = (int) (Math.random() * (double) (maxz - minz) + (double) minz);
        double miny = -1.0D;
        double maxy = 1.0D;
        double ry = Math.random() * (maxy - miny) + miny;
        double x = -5.0D;
        double z = (double) rz;
        return (new Vector(x, ry, z)).normalize();
    }

    public static final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

    public static Vector calculateVelocity(Player p, Entity e) {
        Location ploc = p.getLocation();
        Location eloc = e.getLocation();
        double px = ploc.getX();
        double py = ploc.getY();
        double pz = ploc.getZ();
        double ex = eloc.getX();
        double ey = eloc.getY();
        double ez = eloc.getZ();
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;
        if (px < ex) {
            x = 2.0D;
        } else if (px > ex) {
            x = -2.0D;
        }

        if (py < ey) {
            y = 1.0D;
        } else if (py > ey) {
            y = 1.0D;
        }

        if (pz < ez) {
            z = 2.0D;
        } else if (pz > ez) {
            z = -2.0D;
        }

        return new Vector(x, y, z);
    }

    public static Vector calculateVelocity(Location l, Entity e) {
        Location eloc = e.getLocation();
        double px = l.getX();
        double py = l.getY();
        double pz = l.getZ();
        double ex = eloc.getX();
        double ey = eloc.getY();
        double ez = eloc.getZ();
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;
        if (px < ex) {
            x = 2.0D;
        } else if (px > ex) {
            x = -2.0D;
        }

        if (py < ey) {
            y = 1.0D;
        } else if (py > ey) {
            y = 1.0D;
        }

        if (pz < ez) {
            z = 2.0D;
        } else if (pz > ez) {
            z = -2.0D;
        }

        return new Vector(x, y, z);
    }

    public static double trim(int degree, double d) {
        StringBuilder format = new StringBuilder("#.#");

        for (int twoDForm = 1; twoDForm < degree; ++twoDForm) {
            format.append('#');
        }

        DecimalFormat var5 = new DecimalFormat(format.toString());
        return Double.valueOf(var5.format(d));
    }

    public static int r(int i) {
        return random.nextInt(i);
    }

    public static double offset2d(Entity a, Entity b) {
        return offset2d((Vector) a.getLocation().toVector(), (Vector) b.getLocation().toVector());
    }

    public static double offset2d(Location a, Location b) {
        return offset2d((Vector) a.toVector(), (Vector) b.toVector());
    }

    public static double offset2d(Vector a, Vector b) {
        a.setY(0);
        b.setY(0);
        return a.subtract(b).length();
    }

    public static double offset(Entity a, Entity b) {
        return offset((Vector) a.getLocation().toVector(), (Vector) b.getLocation().toVector());
    }

    public static double offset(Location a, Location b) {
        return offset((Vector) a.toVector(), (Vector) b.toVector());
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }

    public static final float sin(float radians) {
        return UtilMath.Sin.table[(int) (radians * 2607.5945F) & 16383];
    }

    public static final float cos(float radians) {
        return UtilMath.Sin.table[(int) ((radians + 1.5707964F) * 2607.5945F) & 16383];
    }

    public static final float sinDeg(float degrees) {
        return UtilMath.Sin.table[(int) (degrees * 45.511112F) & 16383];
    }

    public static final float cosDeg(float degrees) {
        return UtilMath.Sin.table[(int) ((degrees + 90.0F) * 45.511112F) & 16383];
    }

    public static final float atan2(float y, float x) {
        float add;
        float mul;
        if (x < 0.0F) {
            if (y < 0.0F) {
                y = -y;
                mul = 1.0F;
            } else {
                mul = -1.0F;
            }

            x = -x;
            add = -3.1415927F;
        } else {
            if (y < 0.0F) {
                y = -y;
                mul = -1.0F;
            } else {
                mul = 1.0F;
            }

            add = 0.0F;
        }

        float invDiv = 1.0F / ((x < y ? y : x) * INV_ATAN2_DIM_MINUS_1);
        if (invDiv == 1.0F / 0.0) {
            return ((float) Math.atan2((double) y, (double) x) + add) * mul;
        } else {
            int xi = (int) (x * invDiv);
            int yi = (int) (y * invDiv);
            return (UtilMath.Atan2.table[yi * ATAN2_DIM + xi] + add) * mul;
        }
    }

    public static int random(int range) {
        return random.nextInt(range + 1);
    }

    public static final int random(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }

    public static final boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static final boolean randomBoolean(float chance) {
        return random() < chance;
    }

    public static final float random() {
        return random.nextFloat();
    }

    public static final float random(float range) {
        return random.nextFloat() * range;
    }

    public static final float random(float start, float end) {
        return start + random.nextFloat() * (end - start);
    }

    public static int nextPowerOfTwo(int value) {
        if (value == 0) {
            return 1;
        } else {
            --value;
            value |= value >> 1;
            value |= value >> 2;
            value |= value >> 4;
            value |= value >> 8;
            value |= value >> 16;
            return value + 1;
        }
    }

    public static boolean isPowerOfTwo(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    public static int clamp(int value, int min, int max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static short clamp(short value, short min, short max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static float clamp(float value, float min, float max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static int floor(float x) {
        return (int) ((double) x + 16384.0D) - 16384;
    }

    public static int floorPositive(float x) {
        return (int) x;
    }

    public static int ceil(float x) {
        return (int) ((double) x + 16384.999999999996D) - 16384;
    }

    public static int ceilPositive(float x) {
        return (int) ((double) x + 0.9999999D);
    }

    public static int round(float x) {
        return (int) ((double) x + 16384.5D) - 16384;
    }

    public static int roundPositive(float x) {
        return (int) (x + 0.5F);
    }

    public static boolean isZero(float value) {
        return Math.abs(value) <= 1.0E-6F;
    }

    public static boolean isZero(float value, float tolerance) {
        return Math.abs(value) <= tolerance;
    }

    public static boolean isEqual(float a, float b) {
        return Math.abs(a - b) <= 1.0E-6F;
    }

    public static boolean isEqual(float a, float b, float tolerance) {
        return Math.abs(a - b) <= tolerance;
    }

    private static class Atan2 {
        static final float[] table = new float[16384];

        static {
            for (int i = 0; i < UtilMath.ATAN2_DIM; ++i) {
                for (int j = 0; j < UtilMath.ATAN2_DIM; ++j) {
                    float x0 = (float) i / (float) UtilMath.ATAN2_DIM;
                    float y0 = (float) j / (float) UtilMath.ATAN2_DIM;
                    table[j * UtilMath.ATAN2_DIM + i] = (float) Math.atan2((double) y0, (double) x0);
                }
            }

        }

        private Atan2() {
        }
    }

    private static class Sin {
        static final float[] table = new float[16384];

        static {
            int i;
            for (i = 0; i < 16384; ++i) {
                table[i] = (float) Math.sin((double) (((float) i + 0.5F) / 16384.0F * 6.2831855F));
            }

            for (i = 0; i < 360; i += 90) {
                table[(int) ((float) i * 45.511112F) & 16383] = (float) Math.sin((double) ((float) i * 0.017453292F));
            }

        }

        private Sin() {
        }
    }
}
