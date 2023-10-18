import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useFormik } from "formik";
import * as Yup from "yup";
import { BsEyeSlashFill, BsFillEyeSlashFill } from "react-icons/bs";
import { FaRegFaceRollingEyes } from "react-icons/fa6";
import { IoEyeSharp } from "react-icons/io5";

const LoginForm = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");
    const navigation = useNavigate();

    const validationSchema = Yup.object().shape({
        username: Yup.string().required("Username is required"),
        password: Yup.string().required("Password is required")
    });

    const formik = useFormik({
        initialValues: {
            username: "",
            password: "",
        },
        validationSchema,
        onSubmit: async (values, { setStatus, setSubmitting }) => {
            try {
                const res = await axios.post("http://localhost:8080/api/auth/login", {
                    username: values.username,
                    password: values.password,
                });
                localStorage.setItem("id", res?.data?.id);
                navigation("/dashboard");
            } catch (error) {
                setAlertMessage("Authentication failed. Please check your credentials!");
                setStatus("Authentication failed. Please check your credentials!");

                setTimeout(() => {
                    setAlertMessage('');
                }, 3000)
            }
            setSubmitting(false);
        },
    });

    return (
        <>
            <div className="bg-gradient-to-t from-blue-700 to-blue-900 w-full h-full absolute"></div>
            <form
                onSubmit={formik.handleSubmit}
                className="absolute bg-white px-10 py-8 flex flex-col gap-3 shadow-2xl border border-2 rounded-2xl"
            >
                <h2 className={"text-2xl font-bold mb-2"}>Login</h2>
                <label className="flex flex-col gap-1 text-m font-medium text-gray-900">
                    Username
                    <input
                        name="username"
                        value={formik.values.username}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        type="text"
                        className={`p-2 border border-gray-300 rounded-xl outline-0 ${
                            formik.touched.username && formik.errors.username ? "border-red-500" : ""
                        }`}
                    />
                    {formik.touched.username && formik.errors.username && (
                        <div className="text-red-500">{formik.errors.username}</div>
                    )}
                </label>
                <label className="flex flex-col gap-1 text-m font-medium text-gray-900 relative transition-all">
                    <span
                        className="left-48 top-10 z-30 cursor-pointer absolute"
                        onClick={() => setShowPassword(!showPassword)}
                    >
                        {showPassword ? <IoEyeSharp /> : <BsFillEyeSlashFill />}
                    </span>
                    Password
                    <div className="relative">
                        <input
                            name="password"
                            value={formik.values.password}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            type={showPassword ? "text" : "password"}
                            className={`p-2 border border-gray-300 rounded-xl outline-0 w-full ${
                                formik.touched.password && formik.errors.password ? "border-red-500" : ""
                            }`}
                        />
                    </div>
                    {formik.touched.password && formik.errors.password && (
                        <div className="text-red-500">{formik.errors.password}</div>
                    )}
                </label>
                <div>
                    Not a member?{" "}
                    <Link to="/register" className="text-fuchsia-700 underline font-medium">
                        Sign up
                    </Link>
                </div>
                <button
                    type="submit"
                    className="bg-indigo-700 p-2 rounded-xl text-m w-full text-cyan-50 shadow-sm"
                >
                    Login
                </button>
            </form>
            {alertMessage && (
                <div className="absolute bottom-4 left-auto right-auto bg-red-100 text-red-500 px-4 py-2 rounded-md">
                    {alertMessage}
                </div>
            )}
        </>
    );
};

export default LoginForm;
