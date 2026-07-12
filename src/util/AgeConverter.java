package util;

public class AgeConverter {

    // Converts an age stored as decimal years (e.g., 1.25)
    // into a readable format (e.g., "1 Year 3 Months").
    public static String convertAge(double age) {

        // Convert the decimal year value into the total number of months.
        // Example:
        // 0.50 -> 6 months
        // 1.25 -> 15 months
        int totalMonths = (int) Math.round(age * 12);

        // If the age is less than one year, display only months.
        if (totalMonths < 12) {

            // Use singular form for exactly one month.
            if (totalMonths == 1) {
                return "1 Month";
            }

            // Use plural form for two or more months.
            return totalMonths + " Months";
        }

        // Calculate the number of complete years.
        int years = totalMonths / 12;

        // Calculate the remaining months after removing full years.
        int months = totalMonths % 12;

        // If there are no remaining months,
        // return only the number of years.
        if (months == 0) {

            // Use singular form for one year.
            if (years == 1) {
                return "1 Year";
            }

            // Use plural form for multiple years.
            return years + " Years";
        }

        // Determine whether to use singular or plural
        // for the year and month labels.
        String yearText = (years == 1) ? "Year" : "Years";
        String monthText = (months == 1) ? "Month" : "Months";

        // Return the age in "Years Months" format.
        // Example:
        // 15 months -> "1 Year 3 Months"
        // 33 months -> "2 Years 9 Months"
        return years + " " + yearText + " " + months + " " + monthText;
    }

    // Test the AgeConverter using sample decimal ages.
    public static void main(String[] args) {

        // Sample ages stored as decimal years.
        double[] ages = {
            0.08,
            0.17,
            0.25,
            0.33,
            0.42,
            0.50,
            0.58,
            0.67,
            0.75,
            0.83,
            0.92,
            1.00,
            1.25,
            1.50,
            2.75,
            5.00
        };

        // Display each decimal age together with its converted format.
        for (double age : ages) {
            System.out.printf("%.2f -> %s%n", age, convertAge(age));
        }
    }
}
