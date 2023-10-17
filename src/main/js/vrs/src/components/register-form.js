import React, { useState } from "react";
import { countries } from "../utils/cities";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

const RegisterForm = () => {
    const navigation = useNavigate();
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const initialValues = {
        userName: "",
        mail: "",
        password: "",
        firstName: "",
        lastName: "",
        city: "",
    };

    const validationSchema = Yup.object().shape({
        userName: Yup.string()
            .required("Username is required")
            .min(4, "Username must be at least 4 characters")
            .max(16, "Username must be at most 16 characters"),
        mail: Yup.string()
            .required("Email is required")
            .email("Invalid email address"),
        password: Yup.string()
            .required("Password is required")
            .min(8, "Password must be at least 8 characters")
            .max(16, "Password must be at most 16 characters")
            .matches(
                /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                "Password is not valid"
            ),
        firstName: Yup.string()
            .required("First Name is required")
            .min(2, "First Name must be at least 2 characters")
            .max(15, "First Name must be at most 15 characters"),
        lastName: Yup.string()
            .required("Last Name is required")
            .min(2, "Last Name must be at least 2 characters")
            .max(15, "Last Name must be at most 15 characters"),
        city: Yup.string().required("City is required"),
    });

    const handleSubmit = async (values) => {
        try {
            await new Promise((resolve) => setTimeout(resolve, 1000));
            await axios.post("http://localhost:8080/api/auth/register", values);
            setSuccessMessage("Registration successful!");
            setErrorMessage("");
            navigation("/login");
        } catch (error) {
            setErrorMessage("Registration failed. Please try again.");
            setSuccessMessage("");
        }
    };

    return (
        <>
            <div className={"bg-gradient-to-r from-cyan-600 to-violet-500 w-full h-full absolute"}></div>
            <div className={"absolute bg-white px-10 py-8 flex flex-col gap-3 w-[550px] shadow-2xl border-2 rounded-2xl"}>
                <h2 className={"text-2xl font-bold mb-2"}>Sign Up</h2>
                {successMessage && (
                    <div className="text-green-500 mb-4">{successMessage}</div>
                )}
                {errorMessage && <div className="text-red-500 mb-4">{errorMessage}</div>}
                <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={handleSubmit}>
                    <Form className={"flex flex-col gap-3"}>
                        <div className={"flex flex-row justify-between gap-2"}>
                            <label className={"flex flex-col gap-1 text-m font-medium text-gray-900 w-50"}>
                                Username
                                <Field
                                    name="userName"
                                    type="text"
                                    className={"p-2 border border-gray-300 rounded-xl outline-0"}
                                />
                                <ErrorMessage name="userName" component="div" className="text-red-500" />
                            </label>
                            <label className={"flex flex-col gap-1 text-m font-medium text-gray-900 w-50"}>
                                Mail
                                <Field
                                    name="mail"
                                    type="email"
                                    className={"p-2  border border-gray-300 rounded-xl outline-0"}
                                />
                                <ErrorMessage name="mail" component="div" className="text-red-500" />
                            </label>
                        </div>
                        <div className={"flex flex-row justify-between gap-2"}>
                            <label className={"flex flex-col gap-1 text-m font-medium text-gray-900 w-50"}>
                                First Name
                                <Field
                                    name="firstName"
                                    type="text"
                                    className={"p-2  border border-gray-300 rounded-xl outline-0"}
                                />
                                <ErrorMessage name="firstName" component="div" className="text-red-500" />
                            </label>
                            <label className={"flex flex-col gap-1 text-m font-medium text-gray-900 w-50"}>
                                Last Name
                                <Field
                                    name="lastName"
                                    type="text"
                                    className={"p-2  border border-gray-300 rounded-xl outline-0"}
                                />
                                <ErrorMessage name="lastName" component="div" className="text-red-500" />
                            </label>
                        </div>
                        <div className={"flex flex-row justify-between gap-2"}>
                            <label htmlFor="city" className="text-m font-medium text-gray-900">
                                Select a city
                                <Field
                                    as="select"
                                    name="city"
                                    className="outline-0 border border-gray-300 text-gray-900 text-m rounded-xl focus:ring-blue-500 focus:border-blue-500 block  p-2 mt-1  w-[220px]"
                                >
                                    <option value="" disabled>
                                        Choose a city
                                    </option>
                                    {Object.entries(countries).map(([id, city]) => (
                                        <option key={id} value={id}>
                                            {city}
                                        </option>
                                    ))}
                                </Field>
                                <ErrorMessage name="city" component="div" className="text-red-500 " />
                            </label>
                            <label className={"flex flex-col gap-1 text-m font-medium text-gray-900"}>
                                Password
                                <Field
                                    name="password"
                                    type="password"
                                    className={"p-2  border border-gray-300 rounded-xl outline-0  w-50"}
                                />
                                <ErrorMessage name="password" component="div" className="text-red-500" />
                            </label>
                        </div>
                        <div>
                            Already a member?{" "}
                            <Link to={"/login"} className={"text-fuchsia-700 underline font-medium"}>
                                Login
                            </Link>
                        </div>
                        <button
                            type="submit"
                            className="bg-indigo-700 p-2 rounded-xl text-m w-full text-cyan-50 shadow-sm"
                        >
                            Register
                        </button>
                    </Form>
                </Formik>
            </div>
    </>
);
};

export default RegisterForm;
