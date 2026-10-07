import streamlit as st
import requests
import pandas as pd


# --------------------------------------------------
# PAGE CONFIG
# --------------------------------------------------

st.set_page_config(
    page_title="Customer Intelligence",
    page_icon="🧠",
    layout="centered"
)


# --------------------------------------------------
# HEADER
# --------------------------------------------------

st.title("🧠 Customer Intelligence & Segmentation")

st.write(
    "Analyze customer behavior using RFM analysis "
    "and K-Means clustering."
)

st.divider()


# --------------------------------------------------
# INPUT SECTION
# --------------------------------------------------

st.subheader("👤 Customer RFM Information")

st.caption(
    "Enter the customer's Recency, Frequency and Monetary values."
)

col1, col2 = st.columns(2)

with col1:

    recency = st.number_input(
        "Recency (days)",
        min_value=0,
        max_value=1000,
        value=30,
        step=1
    )

with col2:

    frequency = st.number_input(
        "Frequency (purchases)",
        min_value=1,
        max_value=1000,
        value=10,
        step=1
    )

monetary = st.number_input(
    "Monetary (total spending)",
    min_value=0.0,
    max_value=10000000.0,
    value=50000.0,
    step=1000.0
)


st.divider()


# --------------------------------------------------
# PREDICTION
# --------------------------------------------------

if st.button(
    "🔍 Predict Customer Segment",
    use_container_width=True
):

    data = {
        "recency": recency,
        "frequency": frequency,
        "monetary": monetary
    }

    try:

        response = requests.post(
            "http://3.110.155.47:8000/predict",
            json=data
        )

        if response.status_code == 200:

            result = response.json()

            cluster = result["cluster"]
            segment = result["segment"]


            # ------------------------------------------
            # SUCCESS
            # ------------------------------------------

            st.success("Prediction completed successfully!")


            # ------------------------------------------
            # RESULT
            # ------------------------------------------

            st.subheader("🎯 Customer Segment")

            result_col1, result_col2 = st.columns(2)

            with result_col1:

                st.metric(
                    "Cluster",
                    cluster
                )

            with result_col2:

                st.metric(
                    "Segment",
                    segment
                )


            # ------------------------------------------
            # RFM SUMMARY
            # ------------------------------------------

            st.subheader("📊 RFM Summary")

            rfm_data = pd.DataFrame({
                "Metric": [
                    "Recency",
                    "Frequency",
                    "Monetary"
                ],
                "Value": [
                    recency,
                    frequency,
                    monetary
                ]
            })

            st.dataframe(
                rfm_data,
                use_container_width=True,
                hide_index=True
            )


            # ------------------------------------------
            # SEGMENT DESCRIPTION
            # ------------------------------------------

            st.subheader("💡 Segment Insights")


            if segment == "High-Value / Loyal":

                st.info(
                    "This customer shows strong purchasing activity "
                    "and high monetary value. They can be considered "
                    "an important and loyal customer segment."
                )

                st.write(
                    "**Business Recommendation:** "
                    "Focus on retention, loyalty programs, "
                    "premium offers and personalized rewards."
                )


            elif segment == "Regular / Mid-Value":

                st.info(
                    "This customer shows moderate purchasing "
                    "activity and spending behavior."
                )

                st.write(
                    "**Business Recommendation:** "
                    "Use personalized promotions and "
                    "cross-selling strategies to increase "
                    "engagement and spending."
                )


            elif segment == "Inactive / Low-Value":

                st.warning(
                    "This customer has relatively low purchasing "
                    "activity and may require re-engagement."
                )

                st.write(
                    "**Business Recommendation:** "
                    "Consider targeted offers, reactivation "
                    "campaigns and limited-time discounts."
                )


            # ------------------------------------------
            # RFM VISUALIZATION
            # ------------------------------------------

            st.subheader("📈 Customer RFM Profile")

            chart_data = pd.DataFrame({
                "Metric": [
                    "Recency",
                    "Frequency",
                    "Monetary"
                ],
                "Value": [
                    recency,
                    frequency,
                    monetary
                ]
            })

            st.bar_chart(
                chart_data.set_index("Metric")
            )


        else:

            st.error(
                f"API Error: {response.status_code}"
            )


    except requests.exceptions.ConnectionError:

        st.error(
            "FastAPI server is not running. "
            "Start the API using: "
            "`uvicorn backend.main:app --reload`"
        )